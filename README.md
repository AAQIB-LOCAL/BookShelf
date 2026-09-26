# BookShelf

A simple layered J2EE Book Management application using JSP, Servlets, plain Java business logic, and an in-memory repository.

## Layers

- Client: JSP/HTML pages
- Web: Servlets
- Business: BookService validates input and enforces unique ISBNs
- Data: BookRepository stores books in an in-memory List

## Requirements

- JDK 8 or later
- Apache Tomcat 9
- javax.servlet-api.jar

## Compile on Windows CMD

mkdir WebContent\\WEB-INF\\classes

javac -cp "path\\to\\javax.servlet-api.jar" -d WebContent\\WEB-INF\\classes src\\com\\example\\model\\Book.java src\\com\\example\\data\\BookRepository.java src\\com\\example\\service\\BookService.java src\\com\\example\\servlet\\AddBookServlet.java src\\com\\example\\servlet\\ViewBooksServlet.java

jar -cvf BookShelf.war -C WebContent .

Deploy BookShelf.war to Tomcat webapps and open http://localhost:8080/BookShelf/

Books are stored only in memory and are lost when the application restarts.
