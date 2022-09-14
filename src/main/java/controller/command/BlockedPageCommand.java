package controller.command;

import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import model.entity.Plan;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;
import service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BlockedPageCommand implements Command {
    private static Logger logger = Logger.getLogger(BlockedPageCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In Blocked Page");
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();
        var personService = serviceFactory.getPersonService();
        var planService = serviceFactory.getPlanService();
        String btn = req.getParameter("accept");

        Person person = (Person) req.getSession().getAttribute("person");
        List<Offer> offers = null;
        try {
            offers = offerService.getAllOffersById(person.getId());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        double offerPrices = offers.stream()
                .map(Offer::getPrice)
                .filter(price -> price > person.getFunds())
                .mapToDouble(x -> x)
                .sum();
        double moneyToPay = offerPrices - person.getFunds();

        if (moneyToPay > person.getFunds()) {
            logger.info("User don`t have money to unblock himself");
            req.getSession().setAttribute("moneyToPay", moneyToPay);
            req.setAttribute("noMoney", true);
        }
        if (moneyToPay < person.getFunds()) {
            logger.info("User is blocked by Admin");
            req.setAttribute("blockedByAdmin", true);
        }


        if (Objects.nonNull(btn)) {
            double enteredValue = Double.parseDouble(req.getParameter("depositField"));
            if (enteredValue < moneyToPay) {
                logger.info("Entered value by user is less");
                person.setFunds(person.getFunds() + enteredValue);
                try {
                    personService.update(person);
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }
                req.setAttribute("notEnough", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/blockedPage.jsp");
            } else {
                logger.info("User unbanning");
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                long time = System.currentTimeMillis() + Long.parseLong("2592000000");
                Timestamp newDateEnd = new Timestamp(time);
                try {
                    List<Plan> plans = planService.getPlansByUser(person.getId());
                    for (Plan p : plans) {
                        p.setDate_end(newDateEnd);
                        p.setStatus_id(1);
                        planService.updatePlan(p);
                    }
                } catch (SQLException | NamingException | DataBaseException e) {
                    e.printStackTrace();
                }
                person.setFunds(enteredValue - offerPrices + person.getFunds());
                person.setStatus(1);
                Command command;
                try {
                    personService.update(person);
                    logger.info("User is unbanned, leaving Blocked Page");
                    command = CommandFactory.getCommand("/view/personPage");
                    command.execute(req, resp);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        CommandUtil.goToPage(req, resp, "/WEB-INF/view/blockedPage.jsp");
    }
}
