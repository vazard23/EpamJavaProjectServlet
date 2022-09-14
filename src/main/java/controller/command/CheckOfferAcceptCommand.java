package controller.command;

import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import org.apache.log4j.Logger;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CheckOfferAcceptCommand implements Command {
    private static Logger logger = Logger.getLogger(CheckOfferAcceptCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In CheckOfferAcceptCommand");
        String button = req.getParameter("btn");
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();
        var personService = serviceFactory.getPersonService();
        Person person = (Person) req.getSession().getAttribute("person");
        Double money = person.getFunds();
        int offer_id;
        try {
            if (button.equals("accept")) {
                offer_id = Integer.parseInt(req.getParameter("offer_id"));
                Offer offer = offerService.getOfferById(offer_id);
                req.getSession().setAttribute("offer_id", offer_id);

                if(person.getFunds() > offer.getPrice()){
                    logger.info("Accepting order is successful");
                    offerService.addOfferToPlan(offer.getId(), person.getId());
                    person.setFunds(person.getFunds() - offer.getPrice());
                    personService.update(person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/personPage");
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommandUtil.goToPage(req, resp, "/WEB-INF/view/checkOfferAccept.jsp");
    }
}
