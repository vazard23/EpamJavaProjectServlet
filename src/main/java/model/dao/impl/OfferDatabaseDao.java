package model.dao.impl;

import model.dao.OfferDao;
import model.dao.connection.Connector;
import model.dao.constant.Constants;
import model.entity.Offer;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OfferDatabaseDao implements OfferDao {
    @Override
    public List<Offer> getAllOffersById(int person_id) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.ALL_OFFERS_BY_PERSON_ID)) {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> id_list = new ArrayList<>();
            List<Offer> offers = new ArrayList<Offer>();
            while (resultSet.next()){
                id_list.add(resultSet.getInt(4));
            }
//TODO rename constant to all plans by id, figure out how to change prepared statement int from an array
            PreparedStatement stmt = connection.prepareStatement(Constants.SELECT_BY_ID_OFFER);


            for (Integer integer : id_list) {
                stmt.setInt(1, integer);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
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
        return false;
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
            throw new DataBaseException(String.format("Cannot get book by id=%d", id), e);
        }
    }

    @Override
    public boolean deleteEntity(Integer id) throws NamingException, SQLException {
        return false;
    }

    @Override
    public Offer updateEntity(Offer entity) {
        return null;
    }


    @Override
    public List<Offer> getAllFree() {
        return null;
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
            throw new RuntimeException("Cannot getAllEntity book", e);
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

    private Offer offerBuild(ResultSet rs) throws SQLException{
        Offer offer = new Offer();
        offer.setId(rs.getInt(1));
        offer.setName(rs.getString(2));
        offer.setDescription(rs.getString(3));
        offer.setPrice(rs.getDouble(4));
        offer.setCategory_id(rs.getInt(5));

        return offer;
    }

    private List<Integer> getPlanId(int person_id) throws SQLException{
        Connection connection = Connector.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.ALL_OFFERS_BY_PERSON_ID); {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> id_list = new ArrayList<>();
            List<Offer> offers = new ArrayList<Offer>();
            while (resultSet.next()){
                id_list.add(resultSet.getInt(4));
            }
            return id_list;
    }
}
}
