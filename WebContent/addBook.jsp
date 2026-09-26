<%@ page contentType="text/html;charset=UTF-8" %>
<%!
    private String escapeHtml(Object value) {
        if (value == null) {
            return "";
        }

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
    <title>Add Book - BookShelf</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Add Book</h1>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <%= escapeHtml(request.getAttribute("error")) %>
            </div>
        <% } %>

        <form action="addBook" method="post">
            <div class="form-row">
                <label for="title">Title</label>
                <input type="text" id="title" name="title"
                       value="<%= escapeHtml(request.getAttribute("title")) %>" required>
            </div>

            <div class="form-row">
                <label for="author">Author</label>
                <input type="text" id="author" name="author"
                       value="<%= escapeHtml(request.getAttribute("author")) %>" required>
            </div>

            <div class="form-row">
                <label for="isbn">ISBN</label>
                <input type="text" id="isbn" name="isbn"
                       value="<%= escapeHtml(request.getAttribute("isbn")) %>" required>
            </div>

            <button class="button" type="submit">Add Book</button>
        </form>

        <nav class="nav">
            <a href="index.jsp">Back to Home</a>
            <a href="viewBooks">View Books</a>
        </nav>
    </div>
</div>
</body>
</html>
