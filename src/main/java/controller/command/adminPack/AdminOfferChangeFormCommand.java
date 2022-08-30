package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminOfferChangeFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();
        String change = req.getParameter("change");
        String remove = req.getParameter("remove");
        Offer offer = (Offer) req.getSession().getAttribute("offer");

        if (Objects.nonNull(remove)) {
                try {
                    offerService.planDelete(offer.getId());
                    offerService.deleteEntity(offer.getId());
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminPage");
                    return;
                } catch (NamingException | SQLException | IOException e) {
                    e.printStackTrace();
                }
        }
        if (Objects.nonNull(change)) {
            if (Objects.nonNull(req.getParameter("offer_name"))
                    && Objects.nonNull(req.getParameter("offer_desc"))
                    && Objects.nonNull(req.getParameter("offer_price"))
                    && Objects.nonNull(req.getParameter("categories"))) {
                String name = req.getParameter("offer_name");
                String description = req.getParameter("offer_desc");
                Double price = Double.valueOf(req.getParameter("offer_price"));
                int category = Integer.parseInt(req.getParameter("categories"));

                offer.setName(name);
                offer.setDescription(description);
                offer.setPrice(price);
                offer.setCategory_id(category);

                offerService.updateEntity(offer);

                try {
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminPage");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/offerChangeForm.jsp");
    }
}
