FROM eclipse-temurin:21-alpine-3.22
MAINTAINER Alexandr N <aztek1717@gmail.com>

ARG JAR_FILE=target/homework21-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]