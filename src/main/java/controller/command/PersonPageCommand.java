package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) req.getSession().getAttribute("person");
        req.getSession().setAttribute("name", person.getName());
        req.getSession().setAttribute("funds", person.getFunds());

        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();

            List<Offer> offers = offerService.getAllOffersById(person.getId());
            if(offers.size() == 0){
                req.setAttribute("offerList_Empty", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/personPage.jsp");
                return;
            }

        req.setAttribute("offerList_notEmpty", true);
        req.setAttribute("offers", offers);
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/personPage.jsp");
    }
    }

