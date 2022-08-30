package controller.command;

import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import model.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OfferListPersonCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();
        Person person = (Person) req.getSession().getAttribute("person");
        Integer offer_id = (Integer) req.getSession().getAttribute("offer_id");
        Command command = null;

        if(Objects.nonNull(offer_id)) {
            if (offerService.hasPlan(offer_id, person.getId())) {
                req.setAttribute("hasPlan", true);
            }
        }
        try {
            List<Offer> offers = offerService.getAll();
            req.setAttribute("offers", offers);
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/offerListPerson.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/not_found.jsp");
        }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/offerListPerson.jsp");
    }
}
