package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OfferListPersonCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();

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
