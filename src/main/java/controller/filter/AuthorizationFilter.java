package controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    Logger logger = Logger.getLogger(AuthorizationFilter.class);

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
        String loginURI = req.getContextPath() + "/view/login";
        String registrationURI = req.getContextPath() + "/view/registration";

        boolean loggedIn = session != null && session.getAttribute("person") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean signUpRequest = req.getRequestURI().equals(registrationURI);

        if (loggedIn || loginRequest || signUpRequest) {
            filterChain.doFilter(req, resp);
        } else if (req.getRequestURI().equals("/view/registration")) {
            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/mainPage").forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
