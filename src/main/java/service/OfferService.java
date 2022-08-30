package service;

import model.entity.Offer;
import model.exception.DataBaseException;
import model.exception.ServiceException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface OfferService {

    public boolean add(Offer offer) throws DataBaseException, SQLException, NamingException;

    List<Offer> getAll() throws ServiceException;

    List<Offer> getAllOffersById(int person_id);

    Offer getOfferById(int offer_id) throws DataBaseException;

    boolean addOfferToPlan(int offer_id, int person_id);

    boolean hasPlan(int offer_id, int person_id);

    public Offer updateEntity(Offer offer);

    public boolean deleteEntity(Integer id) throws NamingException, SQLException;

    public boolean planDelete(int offer_id);

}
