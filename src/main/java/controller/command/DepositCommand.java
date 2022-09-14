package controller.command;

import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Person;
import model.exception.NotFoundPersonException;
import model.exception.ServiceException;
import model.exception.WrongDataException;
import org.apache.log4j.Logger;
import service.PersonService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class DepositCommand implements Command{
    private static Logger logger = Logger.getLogger(DepositCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In Deposit Command");
        ServiceFactory factory = ServiceFactory.getInstance();
        PersonService personService = factory.getPersonService();
        Command command = null;

        Person person = (Person) req.getSession().getAttribute("person");
        String button = req.getParameter("btn");
        if(Objects.nonNull(button)){
            if(button.equals("Deposit")){
                logger.info("Depositing money");
                double moneyToAdd = 0;
                double money = Float.parseFloat(req.getParameter("depositField"));
                double existingMoney = person.getFunds();
                moneyToAdd = money + existingMoney;
                person.setFunds(moneyToAdd);
                try {
                    personService.update(person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/personPage");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }


        CommandUtil.goToPage(req, resp, "/WEB-INF/view/deposit.jsp");
    }
}
