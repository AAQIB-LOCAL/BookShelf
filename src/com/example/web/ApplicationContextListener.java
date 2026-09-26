package com.example.web;

import com.example.service.BookService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {
    public static final String BOOK_SERVICE_ATTRIBUTE = "bookService";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(
            BOOK_SERVICE_ATTRIBUTE,
            new BookService()
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        event.getServletContext().removeAttribute(BOOK_SERVICE_ATTRIBUTE);
    }
}
