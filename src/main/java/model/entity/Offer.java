package model.entity;

import model.builder.OfferBuilder;
import model.builder.PersonBuilder;

public class Offer extends Model{
    private int id;
    private String name;
    private String description;
    private double price;
    private int category_id;

    private Offer(OfferBuilderImpl builder){
        super(builder.id);
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.category_id = builder.category_id;
    }


    public static class OfferBuilderImpl implements OfferBuilder{
        private int id;
        private String name;
        private String description;
        private double price;
        private int category_id;

        @Override
        public OfferBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public OfferBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public OfferBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override
        public OfferBuilder setPrice(double price) {
            this.price = price;
            return this;
        }
        @Override
        public Offer build(){
            return new Offer(this);
        }
    }

    public Offer(){
    }
}
