<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Add Book - BookShelf</title></head>
<body>
<h1>Add Book</h1>
<% String error = request.getParameter("error"); %>
<% if (error != null) { %>
<p><strong><%= error %></strong></p>
<% } %>
<form action="addBook" method="post">
<p><label>Title:</label> <input type="text" name="title" required></p>
<p><label>Author:</label> <input type="text" name="author" required></p>
<p><label>ISBN:</label> <input type="text" name="isbn" required></p>
<button type="submit">Add Book</button>
</form>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
