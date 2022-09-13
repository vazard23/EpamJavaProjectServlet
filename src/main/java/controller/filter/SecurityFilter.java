package controller.filter;

import controller.command.utils.Operation;
import model.entity.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        List<String> adminURI =
                Stream.of("/EpamJavaProjectServlet_Web_exploded" + Operation.ADMIN_MENU,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADD_NEW_OFFER,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADD_NEW_PERSON,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADMIN_CHANGE_OFFER,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADMIN_CHANGE_OFFER_FORM,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADMIN_OFFER_DELETE,
                        "/EpamJavaProjectServlet_Web_exploded" + Operation.ADMIN_USER_PAGE).toList();

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);

        Person person = (Person) session.getAttribute("person");

        if (Objects.nonNull(person)) {
            if (person.getAccessLevel() == 3) {
                filterChain.doFilter(req, resp);
            }else {
                req.getRequestDispatcher("/view/personPage").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/view/mainPage").forward(req, resp);
        }

    }
    @Override
    public void destroy() {

    }
}
//                if (isAdmin) {
//                    filterChain.doFilter(req, resp);
//                } else if (isUser && adminURI.contains(req.getRequestURI())) {
//                    req.getRequestDispatcher("/WEB-INF/view/personPage.jsp").forward(req, resp);
//                } else
//                    req.getRequestDispatcher("/view/mainPage").forward(req, resp);
//            } else {
//                req.getRequestDispatcher("/view/mainPage").forward(req, resp);
//            }
//        } else {
//            filterChain.doFilter(req, resp);
//        }