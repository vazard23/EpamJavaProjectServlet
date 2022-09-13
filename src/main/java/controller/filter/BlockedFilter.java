package controller.filter;

import controller.command.Command;
import controller.command.factory.CommandFactory;
import controller.command.utils.CommandUtil;
import model.entity.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class BlockedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("person") != null;
        Person person = (Person) session.getAttribute("person");

        if (Objects.nonNull(person)) {
            if (person.getStatus() == 1) {
                filterChain.doFilter(req, resp);
            } else if(req.getRequestURI().equals(req.getContextPath() + "/view/blockedPage")){
                filterChain.doFilter(req, resp);
            } else if(req.getRequestURI().equals(req.getContextPath() + "/view/logout")){
                filterChain.doFilter(req, resp);
            } else {
                try {
                    Command command = CommandFactory.getCommand("/view/blockedPage");
                    command.execute(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
