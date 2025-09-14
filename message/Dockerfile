FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY message/pom.xml .
COPY message/src ./src
RUN mvn clean package -DskipTests -f pom.xml

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/message-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]

