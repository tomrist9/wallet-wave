FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build


COPY . .


RUN mvn -f wallet-bom/pom.xml clean install -DskipTests
RUN mvn -pl message -am clean package -DskipTests



FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /build/message/target/message-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]




