FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/message-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]




