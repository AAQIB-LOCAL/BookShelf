package com.example.servlet;

import com.example.service.BookService;
import com.example.web.ApplicationContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookServlet extends HttpServlet {
    private BookService getBookService() {
        return (BookService) getServletContext().getAttribute(
            ApplicationContextListener.BOOK_SERVICE_ATTRIBUTE
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getBookService().deleteBook(request.getParameter("isbn"));
            response.sendRedirect(request.getContextPath() + "/viewBooks");
        } catch (IllegalArgumentException exception) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
        }
    }
}