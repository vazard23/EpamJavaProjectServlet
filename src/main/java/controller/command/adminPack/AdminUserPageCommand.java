package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Person;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AdminUserPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var serviceFactory = ServiceFactory.getInstance();
        var personService = serviceFactory.getPersonService();
        String blockButton = req.getParameter("block");
        String unblockButton = req.getParameter("unblock");

        try {
            List<Person> personList = personService.getAllPerson();
            req.setAttribute("persons", personList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(Objects.nonNull(blockButton)) {
            if (req.getParameter("person_id") != null) {
                int person_id = Integer.parseInt(req.getParameter("person_id"));
                try {
                    Person person = personService.getById(person_id);
                    person.setStatus(2);
                    personService.update(person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminUserPage");
                    return;
                } catch (DataBaseException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(Objects.nonNull(unblockButton)){
            if (req.getParameter("person_id") != null) {
                int person_id = Integer.parseInt(req.getParameter("person_id"));
                try {
                    Person person = personService.getById(person_id);
                    person.setStatus(1);
                    personService.update(person);
                    resp.sendRedirect("/EpamJavaProjectServlet_Web_exploded/view/adminUserPage");
                    return;
                } catch (DataBaseException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

        CommandUtil.goToPage(req, resp, "/WEB-INF/view/adminUserPage.jsp");
    }
}

