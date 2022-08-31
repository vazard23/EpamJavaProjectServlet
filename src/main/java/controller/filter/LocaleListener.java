package controller.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class LocaleListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        String defaultLocale = "en_EN";
        event.getSession().setAttribute("language", defaultLocale);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}