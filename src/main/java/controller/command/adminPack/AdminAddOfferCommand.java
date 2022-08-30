package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


public class AdminAddOfferCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();
        String button = req.getParameter("btn");
        int category_id = 0;

        if (Objects.nonNull(button)) {
            String name = req.getParameter("offer_name");
            String description = req.getParameter("offer_desc");
            double price = Double.parseDouble(req.getParameter("offer_price"));
            String category = req.getParameter("offer_category");

            if (Objects.nonNull(name) && Objects.nonNull(description) && Objects.nonNull(category)) {
                switch (category) {
                    case ("TV") -> category_id = 1;
                    case ("Web") -> category_id = 2;
                    case ("Phone") -> category_id = 3;
                }


                Offer offer = new Offer.OfferBuilderImpl()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(price)
                        .setCategoryId(category_id)
                        .build();


                try {
                    List<String> names = offerService.getAll().stream().map(Offer::getName).toList();
                    if(!names.contains(offer.getName())) {
                        offerService.add(offer);
                        resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminPage");
                        return;
                    }
                    else {
                        req.setAttribute("name_exists", true);
                        CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddOffer.jsp");
                    }
                } catch (DataBaseException | SQLException | NamingException | IOException | ServiceException e) {
                    e.printStackTrace();
                }

            }

        }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddOffer.jsp");
    }
}
