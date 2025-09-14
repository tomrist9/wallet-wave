FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/cards-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
