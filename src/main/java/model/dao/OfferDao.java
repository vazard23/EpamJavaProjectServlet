package model.dao;

import model.entity.Offer;
import model.exception.DataBaseException;

import java.sql.Timestamp;
import java.util.List;

public interface OfferDao extends CrudDao<Integer, Offer> {

    List<Offer> getAll() throws DataBaseException;

    List<Offer> getAllOffersById(int person_id) throws DataBaseException;

    boolean addOfferToPlan(int offer_id, int person_id) throws DataBaseException;

    boolean hasPlan(int offer_id, int person_id);

    public boolean planDelete(int offer_id);

    public List<Timestamp> getPlanTime(int person_id);
}
