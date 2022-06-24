package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import model.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PersonPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) req.getSession().getAttribute("person");

        var factory = ServiceFactory.getInstance();
        var offerService = factory.getBOfferService();

        String button = req.getParameter("button");

        if (button != null && button.equals("offer")) {
            int offer_id = Integer.parseInt(req.getParameter("id"));

    //        offerService.setOfferForUser(id_book, person.getId());

        }

        try {
            List<Offer> offers = offerService.getAll();

            req.setAttribute("offers", offers);

            CommandUtil.goToPage(req, resp, "/WEB-INF/view/personPage.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
