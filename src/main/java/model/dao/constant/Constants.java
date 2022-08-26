package model.dao.constant;

public final class Constants {
    private Constants() {}
    //User constants for DB
    public static final String INSERT_PERSON = "INSERT INTO person (name, login, password, e_mail, access_level, funds, blocked_status, role_id) VALUES (?,?,?,?,?,?,?,?)";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE ID = ?";
    public static final String UPDATE_PERSON = "UPDATE person SET name = ?, login = ?, password = ?, e_mail = ?, access_level = ?, funds = ?, blocked_status = ?, role_id = ? WHERE ID = ?";
    public static final String ALL_PERSONS = "SELECT * FROM person";

    //Offers constants
    public static final String INSERT_OFFER = "";
    public static final String ALL_OFFERS = "SELECT * FROM offers";

    //Plan constants
    //public static final String ATTACH_PLAN_TO_USER = "INSERT INTO plan ("
    public static final String ALL_OFFERS_BY_PERSON_ID = "SELECT * FROM plan WHERE person_id = ?";

    //Selecting constants
    public static final String SELECT_BY_ID_PERSON = "SELECT * FROM person WHERE id = ?";
    public static final String SELECT_BY_LOGIN_AND_PASS = "SELECT * FROM person WHERE login = ? AND password = ?";
    public static final String SELECT_BY_LOGIN = "SELECT * FROM person WHERE email = ?";
    public static final String SELECT_BY_ID_OFFER = "SELECT * FROM offers WHERE id = ?";


}
