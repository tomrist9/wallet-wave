# Config Server Microservice

This microservice acts as a centralized configuration server, providing configuration properties for other microservices in a distributed system. Built with Spring Cloud Config Server, it integrates with a Git repository to manage configurations for different environments.

---

## Features
- Centralized configuration management.
- Integration with Git for storing configurations.
- Supports multiple profiles (e.g., `dev`, `prod`).
- Enables dynamic updates of configuration properties without restarting client services.
- Actuator endpoints for monitoring health and readiness.

---

## Requirements

- **Java**: 17 or higher
- **Spring Boot**: 3.1+
- **Dependencies**:
  - `spring-cloud-config-server`
  - `spring-boot-starter-actuator`
- **Git Repository**: Configurations stored in a remote Git repository.
- **RabbitMQ**: For distributed messaging (if used by clients).

---

## Configuration

### application.yml
```yaml
spring:
  application:
    name: configserver
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: "https://github.com/tomrist9/wallet-wave-config.git"
          search-paths: config
          default-label: main
          timeout: 5
          clone-on-start: true
          force-pull: true
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"

management:
  endpoints:
    web:
      exposure:
        include: "*"
  health:
    readiness-state:
      enabled: true
    liveness-state:
      enabled: true
  endpoint:
    health:
      probes:
        enabled: true

server:
  port: 8071
```

### Git Repository Structure
Your Git repository should be structured as follows:
```
config/
  application.yml
  service1-
    dev.yml
    prod.yml
  service2-
    dev.yml
    prod.yml
```

---

## How to Run

### Step 1: Clone the Repository
```bash
git clone https://github.com/tomrist9/wallet-wave.git
```

### Step 2: Build the Application
Use Gradle or Maven to build the project.
```bash
./gradlew build
```
or
```bash
mvn clean package
```

### Step 3: Run the Config Server
```bash
java -jar build/libs/configserver-0.0.1-SNAPSHOT.jar
```

### Step 4: Verify the Service
Access the Config Server at:
```
http://localhost:8071/
```

---

## Endpoints

### Configuration Retrieval
Clients can fetch configurations with the following endpoint:
```
GET /{application}/{profile}/{label}
```
Example:
```
http://localhost:8071/service1/dev/main
```

### Actuator Endpoints
- Health: `http://localhost:8071/actuator/health`
- Readiness: `http://localhost:8071/actuator/health/readiness`

---

## Troubleshooting

### Common Issues

1. **No Such Label Exception**:
   - Ensure the `default-label` exists in the Git repository.
   - Check client configurations for invalid labels.

2. **Connection Refused** (RabbitMQ):
   - Ensure RabbitMQ is running and accessible.
   - Verify `spring.rabbitmq.*` properties.

### Logs
Enable detailed logs for debugging:
```yaml
logging:
  level:
    org.springframework.cloud.config.server: DEBUG
```

---

## Contributing
Feel free to submit issues or pull requests to improve the Config Server.

---

## License
This project is licensed under the [MIT License](LICENSE).

