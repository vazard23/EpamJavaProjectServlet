package service.factory;

import service.OfferService;
import service.PersonService;
import service.impl.OfferServiceImpl;
import service.impl.PersonServiceImpl;
import org.apache.log4j.Logger;

public class ServiceFactory {




    private PersonService personService = new PersonServiceImpl();
    private OfferService offerService = new OfferServiceImpl();


    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {

            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {

    }


    public PersonService getPersonService() {
        return personService;
    }

    public OfferService getBookService() {
        return offerService;
    }

}
