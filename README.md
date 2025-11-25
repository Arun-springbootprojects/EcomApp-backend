# 🛒 ecom-app – Spring Boot E-Commerce Backend

A production-grade **E-commerce backend** built using **Spring Boot 3**, **MongoDB**, **JWT Authentication**, and **Role-Based Role Management**.  
This project demonstrates strong backend engineering skills, modern Spring practices, layered architecture, and clean secure API design.

---

## 🚀 Features

### 🔐 Authentication & Security
- User Registration & Login
- JWT Token Generation + Validation
- Stateless Authentication (No Sessions)
- Role-Based Authorization (`USER`, `ADMIN`)
- Custom UserDetailsService
- Secure Password Hashing (BCrypt)

### 🛍️ Product Module
- Add Product (Admin)
- Update Product (Admin)
- Delete Product (Admin)
- Get Single Product
- Get All Products

### 🛒 Cart Module (User)
- Add item to cart  
- Update cart item quantity  
- Remove item from cart  
- View user cart with total price  
- Cart stored in MongoDB  

### ⚙️ Additional Backend Features
- DTO Validations (Jakarta Validation)
- Global Exception Handler
- Transaction Management
- Logging (SLF4J)
- Clean Controller → Service → Repository architecture
- Swagger/OpenAPI documentation
- Dockerfile & Docker Compose support

---

## 🧰 Tech Stack

| Tech | Purpose |
|------|---------|
| **Java 17+** | Programming Language |
| **Spring Boot 3.x** | Backend Framework |
| **MongoDB Atlas** | NoSQL Database |
| **JWT** | Authentication |
| **Maven** | Build Tool |
| **Docker** | Containerization |
| **Lombok** | Boilerplate Reduction |
| **Swagger / OpenAPI** | API Documentation |

---


