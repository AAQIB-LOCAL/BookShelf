package com.example.servlet;

import com.example.service.BookService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AddBookServlet extends HttpServlet {
    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            bookService.addBook(request.getParameter("title"), request.getParameter("author"), request.getParameter("isbn"));
            response.sendRedirect(request.getContextPath() + "/viewBooks");
        } catch (IllegalArgumentException exception) {
            String error = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8.name());
            response.sendRedirect(request.getContextPath() + "/addBook.jsp?error=" + error);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/addBook.jsp");
    }
}
