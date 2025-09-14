FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
