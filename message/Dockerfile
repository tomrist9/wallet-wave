# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# Bütün layihəni konteynerə at
COPY . .

# Burada parent pom wallet-bom içindədir deyə -f ilə göstəririk
RUN mvn -f wallet-bom/pom.xml -pl message -am clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /build/message/target/message-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]




