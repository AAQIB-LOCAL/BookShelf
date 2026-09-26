package com.example.servlet;

import com.example.service.BookService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewBooksServlet extends HttpServlet {
    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("books", bookService.getAllBooks());
        request.getRequestDispatcher("/viewBooks.jsp").forward(request, response);
    }
}
