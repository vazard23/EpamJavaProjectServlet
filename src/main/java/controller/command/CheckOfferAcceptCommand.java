package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CheckOfferAcceptCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String button = req.getParameter("btn");
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();
        Integer id;
        if (Objects.nonNull(button)) {
            try {
                if (button.equals("accept")) {
                    id = Integer.parseInt(req.getParameter("person_id"));
                    Offer offer = offerService.getOfferById(id);
                    String name = offer.getName();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/checkOfferAccept.jsp");
    }
}
