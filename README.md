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

![Architecture Diagram](screenshots/wallet-wave-architecture.png)

> 💡 **Microservices in Wallet-Wave**

- 🛡️ **Gateway**  
  Routes external traffic to services securely

- ⚙️ **Config Server**  
  Centralized configuration management

- 🔎 **Eureka Server**  
  Service discovery and registration
  ![Eureka Server](screenshots/eureka_resized.jpg)

- 👤 **Accounts Service**  
  Account management (create, update, delete, fetch)

- 💳 **Cards Service**  
  Manage debit/credit cards, card issuance and operations

- 💰 **Loans Service**  
  Loan operations and management

- ✉️ **Message Service**  
  Asynchronous messaging (Kafka / RabbitMQ)


  ## 📊 Observability & Monitoring

Wallet-Wave uses **Spring Boot Actuator**, **Prometheus**, and **Grafana** to provide real-time insight into
microservices health and performance.

### 🔹 Prometheus Targets
Prometheus collects metrics from each service (`/actuator/prometheus`).

![Prometheus Targets](screenshots/prometheus.png)

### 🔹 Grafana Dashboards
Real-time visualization of HTTP requests, service health and system performance.

![Grafana Dashboard](screenshots/grafana_visualization.png)


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


Wallet-Wave is deployed on **Render** with Dockerized microservices.

![Render Deployment](screenshots/2025-10-02_23-10-47.png)

---


---

##  Getting Started

### 🔹 Clone the repository
```bash
git clone https://github.com/tomrist9/wallet-wave.git
cd wallet-wave


```bash


