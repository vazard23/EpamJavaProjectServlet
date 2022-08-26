package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PersonPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) req.getSession().getAttribute("person");
        req.getSession().setAttribute("name", person.getName());
        req.getSession().setAttribute("funds", person.getFunds());

        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();

        String button = req.getParameter("button");

        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            List<Offer> offers = offerService.getAllOffersById(person.getId());

            req.setAttribute("offers", offers);

            CommandUtil.goToPage(req, resp, "/WEB-INF/view/personPage.jsp");
        }
    }
}
