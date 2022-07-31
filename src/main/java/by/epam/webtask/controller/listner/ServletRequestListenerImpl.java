package by.epam.webtask.controller.listner;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

import java.io.UnsupportedEncodingException;

@WebListener
public class ServletRequestListenerImpl implements ServletRequestListener {
    private static final Logger LOG = LogManager.getLogger(ServletRequestListenerImpl.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        setEncoding(servletRequest);
    }

    private void setEncoding(HttpServletRequest servletRequest) {
        try {
            servletRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Incorrect encoding", e);
        }
    }
}
