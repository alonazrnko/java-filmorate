# Filmorate

Backend service for a movie-focused social platform that allows users to interact with movies and with each other.  
The application supports ratings, reviews, friendships, likes, activity feed, search, filtering, and age-based movie classification.

---

## 📖 Overview

Filmorate is a backend application designed to manage movies and user interactions around them.  
In addition to basic movie data management, the system implements social features such as friendships, likes, reviews, and an activity feed that reflects user actions.

The project focuses on backend business logic, REST API design, and clean, maintainable architecture with clear separation of responsibilities.

---

## 🚀 Features

### 🎬 Movie Management
- Create, update, and retrieve movies
- Age rating support (MPA / age-based classification)
- Movie ratings and popularity calculation
- Search movies by title
- Filtering and sorting by genres and directors

### 👤 User Management
- User registration and profile management
- Friendship system between users
- Tracking user interactions with movies

### ⭐ Reviews & Likes
- Add, update, and delete reviews
- Like and dislike reviews
- Aggregate review ratings

### 📰 Activity Feed
- Centralized feed of user actions
- Events for friendships, likes, reviews, and ratings
- Chronological ordering of events

---

## 🧱 Architecture

The application follows a layered architecture with explicit separation of responsibilities.

- **Controller layer**  
  Handles HTTP requests and responses, maps incoming data to DTOs, and delegates execution to the service layer.

- **Service layer**  
  Contains core business logic and orchestrates use cases across multiple domains (movies, users, reviews, events).

- **DAO / Repository layer**  
  Encapsulates all database access logic.
  Uses repository abstractions and storage interfaces to isolate persistence logic from business logic.

  Includes:
  - Repositories for core domain entities (films, users, reviews, likes, events, friendships)
  - Base repository abstractions for shared database operations
  - Mapper components for converting database rows into domain models

- **DTO layer**  
  Data Transfer Objects used for request and response models, isolating API contracts from internal domain entities.

- **Model layer**  
  Domain models representing business entities and relationships.

- **Validation & Exception handling**  
  Centralized validation and custom exception handling to ensure consistent error responses.

This architecture improves maintainability, testability, and scalability of the backend.

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3**
- RESTful API
- **H2** (in-memory database at the current stage)
- SQL, JDBC
- **JUnit 5** (unit and integration testing)
- Maven
- Lombok
- Git / GitHub

---

## 🧪 Testing

- Unit tests for service-layer business logic
- Integration tests for REST endpoints
- Verification of data consistency and business rules

---

## 📂 Project Structure

```text
ru.yandex.practicum.filmorate
 ├── controller
 ├── service
 ├── dao
 │   ├── dto
 │   ├── repository
 │   │   ├── mappers
 │   │   ├── repositories
 │   ├── FilmStorage
 │   └── UserStorage
 ├── model
 ├── validation
 ├── exception
 ├── resources
 └── FilmorateApplication
