<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Book" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Books - BookShelf</title></head>
<body>
<h1>All Books</h1>
<% List<Book> books = (List<Book>) request.getAttribute("books"); %>
<% if (books == null || books.isEmpty()) { %>
<p>No books have been added yet.</p>
<% } else { %>
<table border="1">
<tr><th>Title</th><th>Author</th><th>ISBN</th></tr>
<% for (Book book : books) { %>
<tr><td><%= book.getTitle() %></td><td><%= book.getAuthor() %></td><td><%= book.getIsbn() %></td></tr>
<% } %>
</table>
<% } %>
<p><a href="addBook.jsp">Add Another Book</a></p>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
