package controller.command.adminPack;

import controller.command.Command;
import controller.command.utils.CommandUtil;
import model.entity.Person;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand implements Command {
    private static Logger logger = Logger.getLogger(AdminPageCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In Admin Page");
        Person person = (Person) req.getSession().getAttribute("person");
        req.getSession().setAttribute("name", person.getName());
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/adminPage.jsp");
    }
}
