# 💳 Wallet-Wave – Bank Microservices Platform

![Build](https://img.shields.io/github/actions/workflow/status/tomrist9/wallet-wave/ci.yml?branch=main)
![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![Deploy](https://img.shields.io/badge/Render-Deployed-success)

---

## 🏦 Overview

Wallet-Wave is a **production-grade banking application** built with a **microservices architecture** using  
Java, Spring Boot, Docker, and Kubernetes.  
It is designed to deliver **secure, scalable, and resilient** core banking operations for modern financial services.

---

## 🏛️ Architecture

![Architecture Diagram](C:\Users\hp\wallet-wave\screenshots\wallet-wave-architecture.png)

> 💡 **Microservices in Wallet-Wave**

- 🛡️ **Gateway**  
  Routes external traffic to services securely

- ⚙️ **Config Server**  
  Centralized configuration management

- 🔎 **Eureka Server**  
  Service discovery and registration

- 👤 **Accounts Service**  
  Account management (create, update, delete, fetch)

- 💳 **Cards Service**  
  Manage debit/credit cards, card issuance and operations

- 💰 **Loans Service**  
  Loan operations and management

- ✉️ **Message Service**  
  Asynchronous messaging (Kafka / RabbitMQ)

- 📈 **Observability Stack**  
  Actuator, Prometheus, Grafana, Loki, Tempo


---

## ✨ Features

- ✅ **Microservices Architecture** – clearly defined service boundaries  
- ✅ **Spring Boot & Spring Cloud** – production-ready services  
- ✅ **API Gateway** – secure API routing with Spring Cloud Gateway  
- ✅ **Event-Driven Architecture** – RabbitMQ, Kafka, Spring Cloud Stream  
- ✅ **Resilience & Fault Tolerance** – Resilience4J  
- ✅ **Observability & Monitoring** – Prometheus, Grafana, Loki, Promtail, Tempo  
- ✅ **Security & Authentication** – Spring Security, OAuth2 / OIDC, Keycloak  
- ✅ **Containerization & Orchestration** – Docker, Kubernetes (Helm charts)  
- ✅ **API Documentation** – Swagger / OpenAPI  

---

## 🌐 Deployment (Render)

| Service           | URL                                                                 |
|-------------------|---------------------------------------------------------------------|
| Gateway           | https://wallet-wave.onrender.com                                    |
| Accounts API      | https://wallet-wave.onrender.com/accounts/swagger-ui.html           |
| Loans API         | https://wallet-wave.onrender.com/loans/swagger-ui.html              |
| Eureka Dashboard  | https://wallet-wave.onrender.com/eureka                             |
| Actuator Health   | https://wallet-wave.onrender.com/actuator/health                    |

---

##  Prerequisites

Before running this application, ensure you have:

- **Basic knowledge of Java and Spring framework**.
- **Understanding of RESTful APIs**.
- **Docker and Kubernetes installed**.




---

##  Getting Started

### 🔹 Clone the repository
```bash
git clone https://github.com/tomrist9/wallet-wave.git
cd wallet-wave

📄 [My Java Backend Portfolio (PDF)](https://www.canva.com/design/DAGnsTlPI4E/YWJ12yjDBMzhRCY6rslQAQ/view?utm_content=DAGnsTlPI4E&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=hc0e2a8c572

```bash


