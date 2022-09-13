package service.factory;

import service.OfferService;
import service.PersonService;
import service.PlanService;
import service.impl.OfferServiceImpl;
import service.impl.PersonServiceImpl;
import service.impl.PlanServiceImpl;

public class ServiceFactory {




    private PersonService personService = new PersonServiceImpl();
    private OfferService offerService = new OfferServiceImpl();
    private PlanService planService = new PlanServiceImpl();


    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {

            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {

    }

    public PlanService getPlanService() {return planService; }
    public PersonService getPersonService() {
        return personService;
    }

    public OfferService getOfferService() {
        return offerService;
    }

}
