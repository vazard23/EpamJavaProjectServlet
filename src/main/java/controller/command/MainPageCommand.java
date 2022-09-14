package controller.command;

import controller.command.utils.CommandUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand implements Command {
    private static  Logger logger = Logger.getLogger(MainPageCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In Main Page Command");
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/mainPage.jsp");
    }
}
