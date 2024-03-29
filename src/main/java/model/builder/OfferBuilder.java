package model.builder;

import model.entity.Offer;

public interface OfferBuilder {
    OfferBuilder setId(int id);
    OfferBuilder setName(String name);
    OfferBuilder setDescription(String description);
    OfferBuilder setPrice(double price);
    OfferBuilder setCategoryId(int category_id);
    Offer build();
}
