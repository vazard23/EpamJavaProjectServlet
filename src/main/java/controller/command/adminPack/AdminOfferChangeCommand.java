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

public class AdminOfferChangeCommand implements Command {

    private static Logger logger = Logger.getLogger(AdminOfferChangeCommand.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("in offer change command");
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();


        List<Offer> offers = null;
        try {
            offers = offerService.getAll();
            req.setAttribute("offers", offers);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(req.getParameter("offer_id") != null){
            int offer_id = Integer.parseInt(req.getParameter("offer_id"));
            Offer offer = null;
            try {
                offer = offerService.getOfferById(offer_id);
                req.getSession().setAttribute("offer", offer);
                resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/offerChangeForm");
                return;
            } catch (DataBaseException | IOException e) {
                e.printStackTrace();
            }
        }

        CommandUtil.goToPage(req,resp, "/WEB-INF/view/adminChangeOffer.jsp");
    }
}
