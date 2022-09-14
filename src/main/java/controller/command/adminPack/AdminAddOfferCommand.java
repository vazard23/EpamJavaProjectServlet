package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import org.apache.log4j.Logger;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;



public class AdminAddOfferCommand implements Command {
    private static Logger logger = Logger.getLogger(AdminAddOfferCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In AdminAddOfferCommand");
        var factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();
        String button = req.getParameter("btn");
        int category_id = 0;

        if (Objects.nonNull(button)) {
            logger.info("Parsing new offer data");
            String name = req.getParameter("offer_name");
            String description = req.getParameter("offer_desc");
            double price = Double.parseDouble(req.getParameter("offer_price"));

            if (Objects.nonNull(name) && Objects.nonNull(description)) {
                logger.info("Building new Offer to add");
                int category = Integer.parseInt(req.getParameter("categories"));
                Offer offer = new Offer.OfferBuilderImpl()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(price)
                        .setCategoryId(category)
                        .build();
                try {
                    List<String> names = offerService.getAll().stream().map(Offer::getName).toList();
                    if (!names.contains(offer.getName())) {
                        offerService.add(offer);
                        logger.info("Offer added, redirecting to admin page");
                        resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/admin/adminPage");
                        return;
                    } else {
                        logger.info("Name of offer already exists");
                        req.setAttribute("name_exists", true);
                        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/adminAddOffer.jsp");
                    }
                } catch (DataBaseException | SQLException | NamingException | IOException | ServiceException e) {
                    e.printStackTrace();
                }

            }

        }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/adminAddOffer.jsp");
    }
}
