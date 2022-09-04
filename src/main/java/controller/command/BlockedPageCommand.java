package controller.command;

import controller.command.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockedPageCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {




        CommandUtil.goToPage(req, resp, "WEB-INF/view/blockedPage.jsp");
    }
}
