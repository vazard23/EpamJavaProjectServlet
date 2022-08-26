package model.entity;

public class Plan {
    private int id;
    private int person_id;
    private int offer_id;
    private int status_id;

    public Plan(int id, int person_id, int offer_id, int status_id) {
        this.id = id;
        this.person_id = person_id;
        this.offer_id = offer_id;
        this.status_id = status_id;
    }

}
