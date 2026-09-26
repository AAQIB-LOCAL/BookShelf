<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books - BookShelf</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>All Books</h1>

        <% List<Book> books = (List<Book>) request.getAttribute("books"); %>

        <% if (books == null || books.isEmpty()) { %>
            <p class="muted">No books have been added yet.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>ISBN</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Book book : books) { %>
                        <tr>
                            <td><%= book.getTitle() %></td>
                            <td><%= book.getAuthor() %></td>
                            <td><%= book.getIsbn() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>

        <nav class="nav">
            <a href="addBook.jsp">Add Another Book</a>
            <a href="index.jsp">Back to Home</a>
        </nav>
    </div>
</div>
</body>
</html>
