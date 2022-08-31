package model.dao;

import model.entity.Offer;

import java.sql.Timestamp;
import java.util.List;

public interface OfferDao extends CrudDao<Integer, Offer> {
    List<Offer> getAllFree();

    List<Offer> getAll();

    List<Offer> getAllOffersById(int person_id);

    boolean addOfferToPlan(int offer_id, int person_id);

    boolean hasPlan(int offer_id, int person_id);

    public boolean planDelete(int offer_id);

    public List<Timestamp> getPlanTime(int person_id);
}
