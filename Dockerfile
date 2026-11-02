FROM maven:3.9.9-eclipse-temurin-8 AS build

WORKDIR /workspace
COPY pom.xml .
COPY src ./src
COPY WebContent ./WebContent

RUN mvn --batch-mode --no-transfer-progress clean package

FROM tomcat:9.0-jdk8-temurin

ENV PORT=10000

RUN sed -i 's/port="8080"/port="10000" address="0.0.0.0"/' /usr/local/tomcat/conf/server.xml

COPY --from=build /workspace/target/BookShelf.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 10000

CMD ["catalina.sh", "run"]
