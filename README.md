🌦️ Weather Data Backend API (Spring Boot)
📌 Project Overview

This project is a Spring Boot REST API developed to process and manage nearly two decades of Delhi weather forecast data.

The application:

✅ Loads weather data from a CSV file

✅ Stores structured data in MySQL

✅ Provides REST APIs to retrieve and analyze weather details

✅ Computes monthly temperature statistics (Max, Min, Median)

This project was built as part of a backend assessment.

🛠️ Tech Stack

☕ Java 21

🚀 Spring Boot 3.x

🗄️ Spring Data JPA

🐬 MySQL

📦 Maven

📬 Postman (API Testing)

📂 Project Structure
com.securin.weather
│
├── controller
│     └── WeatherController.java
│
├── service
│     ├── WeatherService.java
│     └── CsvService.java
│
├── repository
│     └── WeatherRepository.java
│
├── model
│     └── Weather.java
│
├── dto
│     └── WeatherStatsDTO.java
│
└── config

The code is implemented in a modular structure following clean architecture principles.

⚙️ Setup Instructions
1️⃣ Clone Repository
git clone https://github.com/your-username/weather-data-backend.git
cd weather-data-backend
2️⃣ Configure MySQL

Create a database:

CREATE DATABASE weather_db;

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Run Application

Using Maven:

mvn spring-boot:run

Or run WeatherDatasetApplication.java from IDE.

Server runs at:

http://localhost:8080
📬 API Endpoints
1️⃣ Upload Weather CSV
🔹 POST /weather/upload

Uploads and stores CSV data into database.

📌 Request (Postman)

Method: POST

Body → form-data

Key: file (Type: File)

Value: Select CSV file

✅ Response
CSV Loaded Successfully!
2️⃣ Get Weather By Date
🔹 GET /weather/date/{date}

Example:

GET /weather/date/1996-11-01

Returns:

Weather condition

Temperature

Humidity

Pressure

DateTime

3️⃣ Get Weather By Month (Across All Years)
🔹 GET /weather/month/{month}

Example:

GET /weather/month/11

Returns all November records across 20 years.

4️⃣ Get Monthly Temperature Statistics (For a Given Year)
🔹 GET /weather/stats/{year}

Example:

GET /weather/stats/1996

Returns:

[
  {
    "month": 1,
    "maxTemperature": 32.4,
    "minTemperature": 12.1,
    "medianTemperature": 21.3
  }
]
📊 Features Implemented

✔ CSV parsing and transformation
✔ Database storage using JPA
✔ Modular service-layer architecture
✔ REST API filtering by date
✔ Month-wise retrieval across 20 years
✔ Monthly max/min/median temperature calculation
✔ Proper JSON response handling

🧠 Design Decisions

Used layered architecture (Controller → Service → Repository)

DTO used for statistics response

Median calculated using sorted temperature list

MySQL used for efficient structured storage

No authentication added (assessment requirement)

🚀 Future Improvements

Add Swagger documentation

Add input validation & exception handling

Add pagination for large dataset

Add caching for performance

Add authentication & authorization

📎 Sample Workflow

Start application

Upload CSV file

Call GET APIs for filtering

Retrieve statistical analysis
