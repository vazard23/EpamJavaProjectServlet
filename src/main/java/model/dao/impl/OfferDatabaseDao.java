package model.dao.impl;

import model.dao.OfferDao;
import model.dao.connection.Connector;
import model.dao.constant.Constants;
import model.entity.Offer;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDatabaseDao implements OfferDao {
    @Override
    public List<Offer> getAllOffersById(int person_id) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.ALL_PLANS_BY_PERSON_ID)) {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> id_list = new ArrayList<>();
            List<Offer> offers = new ArrayList<Offer>();
            while (resultSet.next()) {
                id_list.add(resultSet.getInt(4));
            }
//TODO rename constant to all plans by id, split into methods
            PreparedStatement stmt = connection.prepareStatement(Constants.SELECT_BY_ID_OFFER);
            for (Integer integer : id_list) {
                stmt.setInt(1, integer);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    offers.add(offerBuild(rs));
                }
            }
            return offers;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot getByLoginAndPass person", e);
        }
    }

    @Override
    public boolean add(Offer entity) throws DataBaseException, SQLException, NamingException {
        try (Connection connection = Connector.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(Constants.INSERT_OFFER);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getCategory_id());
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NamingException();
        }
    }

    @Override
    public Offer getById(Integer id) throws DataBaseException {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SELECT_BY_ID_OFFER)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Offer.OfferBuilderImpl()
                    .setId(id)
                    .setName(resultSet.getString("name"))
                    .setDescription(resultSet.getString("description"))
                    .setPrice(resultSet.getDouble("price"))
                    .setCategoryId(resultSet.getInt("category_id"))
                    .build();
        } catch (SQLException e) {
            throw new DataBaseException(String.format("Cannot get offer by id=%d", id), e);
        }
    }

    @Override
    public boolean deleteEntity(Integer id) throws NamingException, SQLException {
        try (Connection con = Connector.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(Constants.DELETE_OFFER)) {
            con.setAutoCommit(false);
            statement.setInt(1, id);
            statement.executeUpdate();
            con.commit();
            return true;
        }
    }

    @Override
    public Offer updateEntity(Offer entity) {
        try (Connection con = Connector.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(Constants.UPDATE_OFFER)) {
            con.setAutoCommit(false);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getCategory_id());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            con.commit();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot update offer ", e);
        }
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.ALL_OFFERS)) {
            ResultSet rs = statement.executeQuery();
            offers = initOffer(rs);
            return offers;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot getAllEntity offer", e);
        }
    }

    @Override
    public boolean addOfferToPlan(int offer_id, int person_id) {
        Long time = System.currentTimeMillis() + Long.parseLong("2592000000");
        Timestamp afterThirtyDays = new Timestamp(time);
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.ATTACH_PLAN_TO_USER)) {
            connection.setAutoCommit(false);
            statement.setInt(1, 1);
            statement.setInt(2, person_id);
            statement.setInt(3, offer_id);
            statement.setTimestamp(4, afterThirtyDays);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add offer to plan table");
        }
    }

    private List<Offer> initOffer(ResultSet rs) throws SQLException {
        List<Offer> offers = new ArrayList<>();
        while (rs.next()) {
            Offer offer = new Offer();
            offer.setId(rs.getInt(1));
            offer.setName(rs.getString(2));
            offer.setDescription(rs.getString(3));
            offer.setPrice(rs.getDouble(4));
            offer.setCategory_id(rs.getInt(5));
            offers.add(offer);
        }
        return offers;
    }

    private Offer offerBuild(ResultSet rs) throws SQLException {
        Offer offer = new Offer();
        offer.setId(rs.getInt(1));
        offer.setName(rs.getString(2));
        offer.setDescription(rs.getString(3));
        offer.setPrice(rs.getDouble(4));
        offer.setCategory_id(rs.getInt(5));

        return offer;
    }

    private List<Integer> getPlanId(int person_id) throws SQLException {
        Connection connection = Connector.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.ALL_PLANS_BY_PERSON_ID);
        {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> id_list = new ArrayList<>();
            List<Offer> offers = new ArrayList<Offer>();
            while (resultSet.next()) {
                id_list.add(resultSet.getInt(4));
            }
            return id_list;
        }
    }

    public boolean hasPlan(int offer_id, int person_id) {
        try {
            List<Integer> plans = getPlanId(person_id);
            if (plans.contains(offer_id)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean planDelete(int offer_id) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PLANS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, offer_id);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Timestamp> getPlanTime(int person_id) {
        List<Timestamp> list = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.PLAN_GET_TIME))
        {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getTimestamp(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
