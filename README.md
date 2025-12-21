🏫 University Management System - Microservices Architecture
<div align="center">
https://img.shields.io/badge/Architecture-Microservices-blue
https://img.shields.io/badge/Backend-Spring%2520Boot-green
https://img.shields.io/badge/Auth-Keycloak-orange
https://img.shields.io/badge/Container-Docker-blue
https://img.shields.io/badge/Database-MySQL-lightgrey
https://img.shields.io/badge/Java-17-red

A modern, scalable university management system built with microservices architecture

</div>
📊 System Architecture
<img width="1732" height="869" alt="image" src="https://github.com/user-attachments/assets/362e3591-239c-42e6-b285-f6693e771911" />
🚀 Features
🎯 Core Functionality
🔐 Role-Based Access Control (Admin, Teacher, Student)

👨‍🎓 Student Management (Enrollment, Grades, History)

👨‍🏫 Teacher Management (Profile, Availability, Courses)

📚 Course Management (Creation, Enrollment, Scheduling)

🗓️ Schedule Planning (Room Booking, Time Slots)

📝 Exam Management (Scheduling, Results, Reports)

⚙️ Admin Dashboard (System Monitoring, User Management)

🛠️ Technical Features
Microservices Architecture with Spring Boot

JWT-based Authentication with Keycloak

Service Discovery with Eureka

API Gateway with Spring Cloud Gateway

Circuit Breaker Pattern for resilience

Distributed Tracing capabilities

Containerized with Docker

📁 Project Structure
<img width="589" height="380" alt="image" src="https://github.com/user-attachments/assets/5c2d5ae2-9f90-4e67-b46c-6d8229aa92a4" />
🛠️ Technology Stack
<img width="817" height="551" alt="image" src="https://github.com/user-attachments/assets/de26434c-2c7e-44fe-af86-8fe38fafc2e2" />
📊 Service Port Mapping
<img width="685" height="547" alt="image" src="https://github.com/user-attachments/assets/c8c3c4b3-b76f-403a-9084-f2d87eab228b" />
🚀 Quick Start
Prerequisites
Java 17+

Maven 3.8+

Docker & Docker Compose && PODMAN
Kubernetees
Installation Steps
1-Clone the Repository
git clone https://github.com/yourusername/university-management-system.git
cd university-management-system
2-Start Infrastructure with Docker
docker-compose up -d
3-Access the Services

Eureka Dashboard: http://localhost:8761

Keycloak Admin: http://localhost:8181

Kafka : http://localhost:8086

API Gateway: http://localhost:8765

H2 Console: http://localhost:8082/h2-console

<img width="755" height="286" alt="image" src="https://github.com/user-attachments/assets/ed7faafc-6602-4132-bfbf-7fd6c1dec6db" />

📚 API Documentation
Sample Endpoints
Student Service
GET    /api/students           # List all students
POST   /api/students           # Create student
GET    /api/students/{id}      # Get student by ID
PUT    /api/students/{id}      # Update student
DELETE /api/students/{id}      # Delete student

Course Service
GET    /api/courses            # List all courses
POST   /api/courses            # Create course (Admin/Teacher)
GET    /api/courses/{id}       # Get course details
PUT    /api/courses/{id}       # Update course (Admin/Teacher)
DELETE /api/courses/{id}       # Delete course (Admin)

Schedule Service
GET    /api/schedules          # View all schedules
POST   /api/schedules          # Create schedule (Admin/Teacher)
GET    /api/schedules/{id}     # Get schedule details
GET    /api/schedules/room/{room}  # Get room schedule
GET    /api/schedules/teacher/{teacherId} # Get teacher schedule

📈 Monitoring & Observability
Eureka Dashboard: Service health and instances

Spring Boot Actuator: Health checks, metrics, info

Logging: Centralized logging with correlation IDs

Tracing & ZABBIX && Grafana && SpringBoot-Actuator: Distributed tracing with Spring Cloud Sleuth

🔧 Configuration Management
Externalized Configuration: Using Spring Cloud Config

Profile-based: dev, test, prod environments

Secret Management: Encrypted properties for sensitive data
📊 Performance Metrics
<img width="690" height="217" alt="image" src="https://github.com/user-attachments/assets/7bb1bd77-ac66-49b4-a49f-c1bffcac958a" />

🔐 Security Features
✅ JWT-based authentication

✅ Role-based access control (RBAC)

✅ HTTPS encryption

✅ SQL injection prevention

✅ Cross-Site Scripting (XSS) protection

✅ Rate limiting

✅ Audit logging

✅ Input validation

✅ Secure password policies

Last updated: December 2024
Version: 1.0.0
© 2024 University Management System. All rights reserved.
