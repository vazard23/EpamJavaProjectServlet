package controller.command.adminPack;

import controller.command.Command;
import controller.command.RegistrationCommand;
import controller.command.utils.CommandUtil;
import controller.command.utils.ValidationData;
import model.entity.Person;
import model.exception.AlreadyExistPersonException;
import model.exception.ServiceException;
import model.exception.WrongDataException;
import org.apache.log4j.Logger;
import service.PersonService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AdminUserRegisterCommand implements Command {

    private static Logger logger = Logger.getLogger(AdminUserRegisterCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var factory = ServiceFactory.getInstance();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (Objects.nonNull(email) && Objects.nonNull(password)) {
            try {
                // if (!ValidationData.isEmailValid(email) || !ValidationData.isPasswordValid(password)) {
                //      throw new WrongDataException();
                 // }

                PersonService personService = factory.getPersonService();

                String name = req.getParameter("name");
                String login = req.getParameter("login");
                int category = Integer.parseInt(req.getParameter("categories"));

                var person = personService.getByLogin(login);

                if (Objects.nonNull(person)) {
                    throw new AlreadyExistPersonException();
                } else {
                    person = new Person.PersonBuilderImpl()
                            .setName(name)
                            .setLogin(login)
                            .setEmail(email)
                            .build();
                    var encrypt = CommandUtil.encrypt(password);
                    person.setPassword(encrypt.orElseThrow(Exception::new));
                    person.setAccessLevel(category);
                    person.setStatus(1);
                    personService.add(person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminPage");
                    return;
                }
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddUser.jsp");
            } catch (WrongDataException e) {
                req.setAttribute("wrongData", true);
                logger.error("Incorrect login or password");
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddUser.jsp");
            } catch (AlreadyExistPersonException e) {
                req.setAttribute("alreadyExist", true);
                logger.error("person already exist");
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddUser.jsp");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminAddUser.jsp");
    }
}
