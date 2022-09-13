package model.dao.factory;


import model.dao.*;

public abstract class DaoFactory {

    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseDaoFactory();
        }
        return instance;
    }

    public abstract PersonDao getPersonDAO();

    public abstract PlanDao getPlanDAO();

    public abstract OfferDao getOfferDao();

}
