package model.entity;

import model.builder.OfferBuilder;
import model.builder.PersonBuilder;

public class Offer extends Model{
    private int id;
    private String name;
    private String description;
    private double price;
    private int category_id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

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

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                '}';
    }
}
