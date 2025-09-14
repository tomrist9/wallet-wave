FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/configserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8071
ENTRYPOINT ["java", "-jar", "app.jar"]

