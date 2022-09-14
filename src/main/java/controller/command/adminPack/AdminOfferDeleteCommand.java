package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import org.apache.log4j.Logger;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminOfferDeleteCommand implements Command {
    private static Logger logger = Logger.getLogger(AdminOfferDeleteCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In AdminOfferDeleteCommand");
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();

        if(req.getParameter("offer_id") != null) {
            logger.info("Parsing offer id...");
            int offer_id = Integer.parseInt(req.getParameter("offer_id"));
                try {
                    logger.info("Trying to delete offer by id: " + offer_id);
                    offerService.planDelete(offer_id);
                    offerService.deleteEntity(offer_id);
                    logger.info("Offer deleted, redirecting to adminChangeOffer");
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/admin/adminChangeOffer");
                    return;
                } catch (NamingException | SQLException | IOException e) {
                    e.printStackTrace();
                }
            }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/offerDelete.jsp");
    }
}
