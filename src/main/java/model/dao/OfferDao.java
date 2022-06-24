package model.dao;

import model.entity.Offer;

import java.util.List;

public interface OfferDao extends CrudDao {
    List<Offer> getAllFree();

    List<Offer> getAll();
}
