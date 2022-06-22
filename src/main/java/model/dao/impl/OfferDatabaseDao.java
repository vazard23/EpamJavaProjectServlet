package model.dao.impl;

import model.dao.OfferDao;
import model.entity.Model;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.SQLException;

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
}
