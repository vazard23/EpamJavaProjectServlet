package controller.command;

import controller.command.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();

        CommandUtil.goToPage(req, resp, "/view/mainPage");
    }
}
