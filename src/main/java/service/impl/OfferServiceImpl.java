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
    public List<Offer> findByOfferName(String text) {
        return null;
    }

    @Override
    public boolean setUserOffer(int user_id, int offer_id) {
     //   Offer offer = null;
      //try {
           //offer = offerDao.getById(offer_id);
    //       offer.setPerson_id(person_id);
        //   offerDao.updateEntity(offer);
           return true;
     //  } catch (DataBaseException e) {
      //    return false;
     //   }
    }

    @Override
    public List<Offer> getAll() throws ServiceException {
        return offerDao.getAll();
    }

    //TODO getUserOffers
    @Override
    public List<Offer> getAllOffers() {
        return null;
    }

    @Override
    public List<Offer> getAllOffersById(int person_id) {
        return offerDao.getAllOffersById(person_id);
    }

    @Override
    public Offer getOfferById(int offer_id) throws DataBaseException {
        return offerDao.getById(offer_id);
    }
}

