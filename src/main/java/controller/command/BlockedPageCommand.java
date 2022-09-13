package controller.command;

import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
import model.entity.Plan;
import model.exception.DataBaseException;
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
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var serviceFactory = ServiceFactory.getInstance();
        var offerService = serviceFactory.getOfferService();
        var personService = serviceFactory.getPersonService();
        var planService = serviceFactory.getPlanService();
        String btn = req.getParameter("accept");

        Person person = (Person) req.getSession().getAttribute("person");
        List<Offer> offers = offerService.getAllOffersById(person.getId());
        Double offerPrices = offers.stream()
                .map(Offer::getPrice)
                .filter(price -> price > person.getFunds())
                .mapToDouble(x -> x)
                .sum();
        double moneyToPay = offerPrices - person.getFunds();

        if (moneyToPay > person.getFunds()) {
            req.getSession().setAttribute("moneyToPay", moneyToPay);
            req.setAttribute("noMoney", true);
        }
        if (moneyToPay < person.getFunds()) {
            req.setAttribute("blockedByAdmin", true);
        }


        if (Objects.nonNull(btn)) {
            double enteredValue = Double.parseDouble(req.getParameter("depositField"));
            if (enteredValue < moneyToPay) {
                person.setFunds(person.getFunds() + enteredValue);
                personService.update(person);
                req.setAttribute("notEnough", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/blockedPage.jsp");
            } else {
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
                personService.update(person);
                Command command;
                try {
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
