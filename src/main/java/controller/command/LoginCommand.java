package controller.command;

import controller.command.utils.CommandUtil;
import model.entity.Person;
import model.exception.NotFoundPersonException;
import model.exception.WrongDataException;
import org.apache.log4j.Logger;
import service.PersonService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory factory = ServiceFactory.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            PersonService personService = factory.getPersonService();

            try {
                var encrypt = CommandUtil.encrypt(password);
                Person person = personService.getByLoginAndPass(login, encrypt.orElseThrow(Exception::new));

                if (person.getStatus() == 1) {
                    req.getSession().setAttribute("person", person);

                    String page = CommandUtil.getPersonPageByRole(person.getAccessLevel());

                    CommandUtil.goToPage(req, resp, page);
                } else {
                    req.setAttribute("errorMessage", true);
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
