package controller.command;

import controller.command.utils.CommandUtil;
import model.dao.impl.PlanDatabaseDao;
import model.entity.Offer;
import model.entity.Person;
import model.entity.Plan;
import model.exception.NotFoundPersonException;
import model.exception.WrongDataException;
import org.apache.log4j.Logger;
import service.PersonService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoginCommand implements Command{
    private static Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In login page");
        ServiceFactory factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();
        var planService = factory.getPlanService();


        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            logger.info("Trying to login by Login and Pass(Not Null)");
            PersonService personService = factory.getPersonService();

            try {
                var encrypt = CommandUtil.encrypt(password);
                Person person = personService.getByLoginAndPass(login, encrypt.orElseThrow(Exception::new));

                Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                List<Plan> plans = planService.getPlansByUser(person.getId());
                List<Timestamp> timeList = new ArrayList<>();
                List<Offer> offers = offerService.getAllOffersById(person.getId());
                List<Double> prices = new ArrayList<>();

                for(Offer d: offers){
                    prices.add(d.getPrice());
                }

                double offerPrices = prices.stream().mapToDouble(d -> d).sum();

                for(Plan p: plans){
                    timeList.add(p.getDate_end());
                }



                for (Timestamp t: timeList)
                {
                    if(currentTime.after(t) && person.getFunds() < offerPrices){
                        logger.info("User is being banned due tariff end and insufficient funds, redirecting to Blocked Page");
                        person.setStatus(2);
                        personService.update(person);
                        req.getSession().setAttribute("person", person);
                        resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/blockedPage");
                        return;
                    }
                }

                if (person.getStatus() == 1) {
                    logger.info("User logging in");
                    req.getSession().setAttribute("person", person);

                    String page = CommandUtil.getPersonPageByRole(person.getAccessLevel());

                    CommandUtil.goToPage(req, resp, page);
                } else {
                    logger.info("User is blocked by Admin, redirecting to Blocked Page");
                    person.setStatus(2);
                    personService.update(person);
                    req.getSession().setAttribute("person", person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/blockedPage");
                    return;
                }

            } catch (NotFoundPersonException e) {
                logger.warn("Not found person in Login Command");
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
            } catch (WrongDataException e) {
                logger.warn("Wrong data in Login Command");
                req.setAttribute("wrongData", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
            } catch (Exception e) {
                logger.warn("Unhandled exception in Login Command");
                e.printStackTrace();
            }
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
    }
    }
