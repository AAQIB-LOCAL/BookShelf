package com.example.servlet;

import com.example.model.Book;
import com.example.service.BookService;
import com.example.web.ApplicationContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBookServlet extends HttpServlet {
    private BookService getBookService() {
        return (BookService) getServletContext().getAttribute(
            ApplicationContextListener.BOOK_SERVICE_ATTRIBUTE
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        Book book = getBookService().searchBooks(isbn).stream()
            .filter(candidate -> candidate.getIsbn().equalsIgnoreCase(isbn == null ? "" : isbn.trim()))
            .findFirst().orElse(null);

        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
            return;
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/editBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        try {
            getBookService().updateBook(isbn, title, author);
            response.sendRedirect(request.getContextPath() + "/viewBooks");
        } catch (IllegalArgumentException exception) {
            request.setAttribute("error", exception.getMessage());
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("isbn", isbn);
            request.getRequestDispatcher("/editBook.jsp").forward(request, response);
        }
    }
}