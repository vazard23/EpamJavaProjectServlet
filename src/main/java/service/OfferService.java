package service;

import model.entity.Offer;
import model.exception.ServiceException;

import java.util.List;

public interface OfferService {
    public List<Offer> findByOfferName(String text);

    public boolean setUserOffer(int user_id, int offer_id);

    List<Offer> getAll() throws ServiceException;

    public List<Offer> getAllOffers();

    Offer getOfferbyPerson(int person_id);

}
