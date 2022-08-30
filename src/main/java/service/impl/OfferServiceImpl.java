package service.impl;

import controller.command.utils.CommandUtil;

import model.dao.OfferDao;
import model.dao.PersonDao;
import model.dao.factory.DaoFactory;

import model.entity.Offer;
import model.entity.Person;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import org.apache.log4j.Logger;

import service.OfferService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OfferServiceImpl implements OfferService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private OfferDao offerDao = daoFactory.getOfferDao();
    private PersonDao personDao = daoFactory.getPersonDAO();


    @Override
    public boolean add(Offer offer) throws DataBaseException, SQLException, NamingException {
        return offerDao.add(offer);
    }


    @Override
    public List<Offer> getAll() throws ServiceException {
        return offerDao.getAll();
    }

    @Override
    public List<Offer> getAllOffersById(int person_id) {
        return offerDao.getAllOffersById(person_id);
    }

    @Override
    public Offer getOfferById(int offer_id) throws DataBaseException {
        return offerDao.getById(offer_id);
    }

    @Override
    public boolean addOfferToPlan(int offer_id, int person_id) {
        return offerDao.addOfferToPlan(offer_id, person_id);
    }

    @Override
    public boolean hasPlan(int offer_id, int person_id) {
        return offerDao.hasPlan(offer_id, person_id);
    }

    @Override
    public Offer updateEntity(Offer offer) {
        return offerDao.updateEntity(offer);
    }

    @Override
    public boolean deleteEntity(Integer id) throws NamingException, SQLException {
        return offerDao.deleteEntity(id);
    }

    @Override
    public boolean planDelete(int offer_id) {
        return offerDao.planDelete(offer_id);
    }
}

