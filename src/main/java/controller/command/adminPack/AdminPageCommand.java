package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) req.getSession().getAttribute("person");
        req.getSession().setAttribute("name", person.getName());
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/adminPage.jsp");
    }
}
