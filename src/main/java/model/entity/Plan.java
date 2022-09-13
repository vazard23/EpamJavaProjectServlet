package model.entity;

import model.builder.PersonBuilder;
import model.builder.PlanBuilder;

import java.sql.Timestamp;

public class Plan extends Model {
    private int id;
    private int person_id;
    private int offer_id;
    private int status_id;
    private Timestamp date_end;

    public Plan(){

    }

    public Plan(PlanBuilderImpl builder) {
        this.id = builder.id;
        this.person_id = builder.person_id;
        this.offer_id = builder.offer_id;
        this.status_id = builder.status_id;
        this.date_end = builder.date_end;
    }

    public static class PlanBuilderImpl implements PlanBuilder{
        private int id;
        private int person_id;
        private int offer_id;
        private int status_id;
        private Timestamp date_end;

        @Override
        public PlanBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public PlanBuilder setStatus(int id) {
            this.status_id = id;
            return this;
        }

        @Override
        public PlanBuilder setPerson(int id) {
            this.person_id = id;
            return this;
        }

        @Override
        public PlanBuilder setOffer(int id) {
            this.offer_id = id;
            return this;
        }

        @Override
        public PlanBuilder setDateEnd(Timestamp timestamp) {
            this.date_end = timestamp;
            return this;
        }

        @Override
        public Plan build() {
            return new Plan(this);
        }
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public void setDate_end(Timestamp date_end) {
        this.date_end = date_end;
    }
}
