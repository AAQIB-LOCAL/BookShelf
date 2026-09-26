package com.example.servlet;

import com.example.service.BookService;
import com.example.web.ApplicationContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewBooksServlet extends HttpServlet {
    private BookService getBookService() {
        return (BookService) getServletContext().getAttribute(
            ApplicationContextListener.BOOK_SERVICE_ATTRIBUTE
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("books", getBookService().getAllBooks());
        request.getRequestDispatcher("/viewBooks.jsp").forward(request, response);
    }
}
