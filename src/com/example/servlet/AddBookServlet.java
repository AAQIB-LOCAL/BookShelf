package com.example.servlet;

import com.example.service.BookService;
import com.example.web.ApplicationContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    private BookService getBookService() {
        return (BookService) getServletContext().getAttribute(
            ApplicationContextListener.BOOK_SERVICE_ATTRIBUTE
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");

        try {
            getBookService().addBook(title, author, isbn);
            response.sendRedirect(request.getContextPath() + "/viewBooks");
        } catch (IllegalArgumentException exception) {
            request.setAttribute("error", exception.getMessage());
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("isbn", isbn);
            request.getRequestDispatcher("/addBook.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/addBook.jsp");
    }
}
