# Build stage
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

COPY src ./src
COPY pom.xml .

RUN mvn clean package # -DskipTests

Run stage
FROM openjdk:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/intershop.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]