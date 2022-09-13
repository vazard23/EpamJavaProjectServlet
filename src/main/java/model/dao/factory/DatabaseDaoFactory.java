package model.dao.factory;


import model.dao.*;
import model.dao.PersonDao;
import model.dao.impl.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDaoFactory extends DaoFactory{



    private PersonDao personDao = new PersonDatabaseDao();
    private OfferDao offerDao = new OfferDatabaseDao();
    private PlanDao planDao = new PlanDatabaseDao();


    @Override
    public PersonDao getPersonDAO() {

        return personDao;
    }

    @Override
    public PlanDao getPlanDAO(){
        return planDao;
    }

    @Override
    public OfferDao getOfferDao() {

        return offerDao;
    }
}
