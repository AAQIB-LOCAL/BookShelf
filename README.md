# BookShelf

A small, layered Java web application for managing a book list. It uses JSP, Java Servlets, plain Java business logic, and an in-memory repository to demonstrate traditional J2EE architecture without adding a framework or database.

## Demo

**Live demo:** Deploy the repository using the [Render Blueprint](https://render.com/docs/blueprint-spec). Once the service is created, add its generated `onrender.com` URL here.

The demo is intentionally ephemeral. Books are held in memory and are cleared whenever the application restarts or is redeployed. Render's free web services can spin down after inactivity, so treat the hosted instance as a portfolio demonstration rather than a persistent service.

## Features

- Add a book with title, author, and ISBN
- Validate required fields and reject duplicate ISBN values
- View all books
- Search by title, author, or ISBN
- Edit a book's title and author
- Delete a book
- Preserve submitted values when add/edit validation fails
- Escape user-provided values before rendering them in JSP pages

## Architecture

```text
Browser
  -> JSP / HTML
  -> Servlet
  -> BookService
  -> BookRepository
  -> In-memory List<Book>
```

- **Presentation:** JSP pages and shared CSS
- **Web:** Servlets handle HTTP requests and responses
- **Business:** `BookService` owns normalization, validation, CRUD operations, and search
- **Data:** `BookRepository` manages the in-memory collection
- **Lifecycle:** `ApplicationContextListener` creates the shared service and stores it in `ServletContext`

See [Architecture](docs/architecture.md) for component responsibilities and request flows.

## Technology

- Java 8
- JSP and Java Servlet 3.1 (`javax.servlet`)
- Apache Tomcat 9
- Maven WAR packaging
- Docker for repeatable deployment
- GitHub Actions for build verification

## Run locally

### Requirements

- JDK 8 or later
- Maven 3.8+
- Apache Tomcat 9 (for running the WAR)

Build the WAR:

```bash
mvn clean verify
```

The deployable artifact is:

```text
target/BookShelf.war
```

Copy it to Tomcat's `webapps` directory and start Tomcat. Open:

```text
http://localhost:8080/BookShelf/
```

### Run with Docker

Build and start the Tomcat 9 container:

```bash
docker build -t bookshelf .
docker run --rm -p 10000:10000 bookshelf
```

Open `http://localhost:10000/`.

## Deploy to Render

This repository includes a `render.yaml` Blueprint and a Dockerfile.

1. Sign in to [Render](https://render.com/).
2. Choose **New** and then **Blueprint**.
3. Connect the `AAQIB-LOCAL/BookShelf` GitHub repository.
4. Select the `main` branch and apply the `bookshelf-demo` service.
5. Wait for the Docker build and health check to complete.
6. Copy the generated service URL into the **Demo** section above. Render may add a suffix if that service name is already taken.

The container runs Tomcat 9 on port 10000, matching Render's default web-service port. Render terminates HTTPS at its edge and forwards requests to the container over HTTP.

## Data lifecycle

The repository uses a Java collection rather than a database. Data exists only in the running application process and is lost when that process restarts, the service scales to a new instance, or a new deployment replaces it. This is an intentional scope decision for this project.

## Project documentation

- [Layered architecture and request flows](docs/architecture.md)
- [Demo walkthrough and screenshot checklist](docs/demo-walkthrough.md)

## Build status

The GitHub Actions workflow runs `mvn clean verify` for pushes and pull requests targeting `main`.
