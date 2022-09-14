package controller.command;

import controller.command.utils.CommandUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static  Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Logout Command");
        req.getSession().invalidate();
        CommandUtil.goToPage(req, resp, "/view/mainPage");
    }
}
