# Mediscreen Sprint 1 - Patient Demographics

This project is a **Spring Boot** application for managing patient demographics. It allows users to view patient details and add patients to the database.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)

---

## Features

- View all patients
- Edit demographic records for existing patient
- Add a patient
- Dockerized deployment for easy setup and scalability

---

## Technologies Used

- **Java**
- **Spring Boot**
- **Thymeleaf**
- **Maven**
- **Docker**
- **PostgresSQL**

---

## Setup Instructions

To run the project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ichen12345/mediscreen_demographics_new.git
2. Run `mvn clean install` to build the project
3. Run the application with docker using `docker compose up -d`
4. Verify that the application is running: Open your browser and navigate to http://localhost:8081/patients to view the list of patients.
