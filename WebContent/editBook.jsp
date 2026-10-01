<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.model.Book" %>
<%!
    private String escapeHtml(Object value) {
        if (value == null) return "";
        return String.valueOf(value).replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    Book book = (Book) request.getAttribute("book");
    String isbn = request.getAttribute("isbn") != null ? String.valueOf(request.getAttribute("isbn")) : (book == null ? "" : book.getIsbn());
    String title = request.getAttribute("title") != null ? String.valueOf(request.getAttribute("title")) : (book == null ? "" : book.getTitle());
    String author = request.getAttribute("author") != null ? String.valueOf(request.getAttribute("author")) : (book == null ? "" : book.getAuthor());
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Edit Book - BookShelf</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="container"><div class="card"><h1>Edit Book</h1>
<% if (request.getAttribute("error") != null) { %><div class="error"><%= escapeHtml(request.getAttribute("error")) %></div><% } %>
<form action="editBook" method="post">
<div class="form-row"><label for="isbn">ISBN</label><input type="text" id="isbn" name="isbn" value="<%= escapeHtml(isbn) %>" readonly></div>
<div class="form-row"><label for="title">Title</label><input type="text" id="title" name="title" value="<%= escapeHtml(title) %>" required></div>
<div class="form-row"><label for="author">Author</label><input type="text" id="author" name="author" value="<%= escapeHtml(author) %>" required></div>
<button class="button" type="submit">Save Changes</button></form>
<nav class="nav"><a href="viewBooks">Back to Books</a></nav></div></div></body></html>
