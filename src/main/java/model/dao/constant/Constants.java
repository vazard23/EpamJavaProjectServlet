package model.dao.constant;

public final class Constants {
    private Constants() {}
    //User constants for DB
    public static final String INSERT_PERSON = "INSERT INTO person (name, login, password, e_mail, access_level, funds, blocked_status, role_id) VALUES (?,?,?,?,?,?,?,?)";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE ID = ?";
    public static final String UPDATE_PERSON = "UPDATE person SET name = ?, login = ?, password = ?, e_mail = ?, access_level = ?, funds = ?, blocked_status = ?, role_id = ? WHERE ID = ?";
    public static final String ALL_PERSONS = "SELECT * FROM person";

    //Offers constants
    public static final String INSERT_OFFER = "INSERT INTO offers (name, description, price, category_id) VALUES (?, ?, ?, ?)";
    public static final String ALL_OFFERS = "SELECT * FROM offers";
    public static final String UPDATE_OFFER = "UPDATE offers SET name = ?, description = ?, price = ?, category_id = ? WHERE ID = ?";
    public static final String DELETE_OFFER = "DELETE FROM offers WHERE ID = ?";

    //Plan constants
    public static final String ATTACH_PLAN_TO_USER = "INSERT INTO plan (status_id, person_id, offer_id, date_end) VALUES (?,?,?,?)";
    public static final String ALL_PLANS_BY_PERSON_ID = "SELECT * FROM plan WHERE person_id = ?";
    public static final String DELETE_PLANS = "DELETE FROM plan WHERE offer_id = ?";
    public static final String PLAN_GET_TIME = "SELECT date_end FROM plan WHERE person_id = ?";


    //Selecting constants
    public static final String SELECT_BY_ID_PERSON = "SELECT * FROM person WHERE id = ?";
    public static final String SELECT_BY_LOGIN_AND_PASS = "SELECT * FROM person WHERE login = ? AND password = ?";
    public static final String SELECT_BY_LOGIN = "SELECT * FROM person WHERE login = ?";
    public static final String SELECT_BY_ID_OFFER = "SELECT * FROM offers WHERE id = ?";


}
