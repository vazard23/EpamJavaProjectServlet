package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminOfferDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();

        if(req.getParameter("offer_id") != null) {
            int offer_id = Integer.parseInt(req.getParameter("offer_id"));
                try {
                    offerService.planDelete(offer_id);
                    offerService.deleteEntity(offer_id);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminChangeOffer");
                    return;
                } catch (NamingException | SQLException | IOException e) {
                    e.printStackTrace();
                }
            }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/offerDelete.jsp");
    }
}
