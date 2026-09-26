# BookShelf

A small layered J2EE Book Management application built with JSP, Servlets, plain Java business logic, and an in-memory repository.

## Architecture

Request flow:

```text
Browser
   |
   v
JSP / HTML
   |
   v
Servlet
   |
   v
BookService
   |
   v
BookRepository
   |
   v
In-memory List<Book>
```

The application keeps the responsibilities separated:

- **Client layer:** JSP/HTML presentation
- **Web layer:** Servlets and application lifecycle listener
- **Business layer:** BookService validates input and enforces the unique-ISBN rule
- **Data layer:** BookRepository owns the in-memory collection

The shared BookService instance is created when the web application starts through ApplicationContextListener and stored in ServletContext. Servlets retrieve it from the application context instead of managing its lifecycle themselves.

## Current Features

- Add a book with title, author, and ISBN
- Required-field validation
- Duplicate ISBN validation
- View all books
- Request-scoped validation errors
- Preserve submitted form values after validation failures
- Basic consistent JSP styling
- WAR deployment to Apache Tomcat

## Requirements

- JDK 8 or later
- Apache Tomcat 9
- javax.servlet-api.jar

## Compile on Windows CMD

Create the classes directory:

```cmd
mkdir WebContent\WEB-INF\classes
```

Compile:

```cmd
javac -cp "path\to\javax.servlet-api.jar" -d WebContent\WEB-INF\classes src\com\example\model\Book.java src\com\example\data\BookRepository.java src\com\example\service\BookService.java src\com\example\web\ApplicationContextListener.java src\com\example\servlet\AddBookServlet.java src\com\example\servlet\ViewBooksServlet.java
```

Package:

```cmd
jar -cvf BookShelf.war -C WebContent .
```

Deploy BookShelf.war to Tomcat webapps and open:

```text
http://localhost:8080/BookShelf/
```

Books are stored only in memory and are lost when the application restarts.
