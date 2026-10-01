<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Book" %>
<%!
    private String escapeHtml(Object value) {
        if (value == null) return "";
        return String.valueOf(value)
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
    }
%>
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
        <form class="search" action="viewBooks" method="get">
            <label for="q">Search</label>
            <input type="search" id="q" name="q" value="<%= escapeHtml(request.getAttribute("query")) %>" placeholder="Title, author or ISBN">
            <button class="button" type="submit">Search</button>
        </form>
        <% List<Book> books = (List<Book>) request.getAttribute("books"); %>
        <% if (books == null || books.isEmpty()) { %>
            <p class="muted">No matching books found.</p>
        <% } else { %>
            <table>
                <thead><tr><th>Title</th><th>Author</th><th>ISBN</th><th>Actions</th></tr></thead>
                <tbody>
                <% for (Book book : books) { %>
                    <tr>
                        <td><%= escapeHtml(book.getTitle()) %></td>
                        <td><%= escapeHtml(book.getAuthor()) %></td>
                        <td><%= escapeHtml(book.getIsbn()) %></td>
                        <td>
                            <a href="editBook?isbn=<%= java.net.URLEncoder.encode(book.getIsbn(), "UTF-8") %>">Edit</a>
                            <form class="inline-form" action="deleteBook" method="post">
                                <input type="hidden" name="isbn" value="<%= escapeHtml(book.getIsbn()) %>">
                                <button type="submit" onclick="return confirm('Delete this book?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        <% } %>
        <nav class="nav"><a href="addBook.jsp">Add Another Book</a><a href="index.jsp">Back to Home</a></nav>
    </div>
</div>
</body>
</html>
