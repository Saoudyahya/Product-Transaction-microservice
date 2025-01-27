# Use a base image with JDK
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/Product-microservice-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9091


ENTRYPOINT ["java", "-jar", "app.jar"]
