package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Offer;
import model.entity.Person;
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
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory factory = ServiceFactory.getInstance();
        var offerService = factory.getOfferService();


        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            PersonService personService = factory.getPersonService();

            try {
                var encrypt = CommandUtil.encrypt(password);
                Person person = personService.getByLoginAndPass(login, encrypt.orElseThrow(Exception::new));

                List<Timestamp> timeList = offerService.getPlanTime(person.getId());
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                List<Offer> debtOffers = new ArrayList<>();

                for (Timestamp t: timeList)
                {
                    if(currentTime.after(t)){
                        person.setStatus(2);
                        personService.update(person);
                        resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/blockedPage");
                    }
                }

                if (person.getStatus() == 1) {
                    req.getSession().setAttribute("person", person);

                    String page = CommandUtil.getPersonPageByRole(person.getAccessLevel());

                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded" + page);
                    return;
                    //CommandUtil.goToPage(req, resp, page);
                } else {
                    req.setAttribute("errorMessage", true);
                    CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
                }

            } catch (NotFoundPersonException e) {
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
            } catch (WrongDataException e) {
                req.setAttribute("wrongData", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
    }
    }
