package controller.servlet;

import controller.command.Command;
import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType ("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String path = req.getRequestURI();
        path = path.substring(path.indexOf("view") - 1);

        Command command = null;
        try {
            command = CommandFactory.getCommand(path);
            command.execute(req, resp);
        } catch (Exception e) {
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/not_found.jsp");
        }
    }
}
