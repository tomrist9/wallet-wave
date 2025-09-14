FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/loans-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]
