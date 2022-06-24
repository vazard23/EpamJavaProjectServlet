package model.dao.impl;

import model.dao.OfferDao;
import model.dao.connection.Connector;
import model.dao.constant.Constants;
import model.entity.Model;
import model.entity.Offer;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDatabaseDao implements OfferDao {
    @Override
    public boolean add(Model entity) throws DataBaseException, SQLException, NamingException {
        return false;
    }

    @Override
    public Model getById(Object id) throws DataBaseException {
        return null;
    }

    @Override
    public boolean deleteEntity(Object id) throws NamingException, SQLException {
        return false;
    }

    @Override
    public Model updateEntity(Model entity) {
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
}
