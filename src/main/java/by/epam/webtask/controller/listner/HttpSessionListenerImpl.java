package by.epam.webtask.controller.listner;

import by.epam.webtask.controller.command.RequestParameter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static by.epam.webtask.controller.command.Attribute.GLOBAL_SESSION_MAP;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    public static Map<Long, HttpSession> getSessionMap(ServletContext appContext) {
        Map<Long, HttpSession> sessionMap = (Map<Long, HttpSession>) appContext.getAttribute(GLOBAL_SESSION_MAP);
        if (sessionMap == null) {
            sessionMap = new ConcurrentHashMap<>();
            appContext.setAttribute(GLOBAL_SESSION_MAP, sessionMap);
        }
        return sessionMap;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        setDefaultLocale(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        removeFromSessionMap(se);
    }

    private void removeFromSessionMap(HttpSessionEvent se) {
        Map<Long, HttpSession> sessionMap = getSessionMap(se.getSession().getServletContext());
        sessionMap.values().remove(se.getSession());
    }

    private void setDefaultLocale(HttpSession session) {
        session.setAttribute(RequestParameter.LOCALE, Locale.getDefault().toString());
    }
}
