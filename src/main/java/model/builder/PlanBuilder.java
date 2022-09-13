package model.builder;

import model.entity.Offer;
import model.entity.Plan;

import java.sql.Timestamp;

public interface PlanBuilder {
    PlanBuilder setId(int id);
    PlanBuilder setStatus(int id);
    PlanBuilder setPerson(int id);
    PlanBuilder setOffer(int id);
    PlanBuilder setDateEnd(Timestamp timestamp);
    Plan build();
}

