# ReVastra - Smart Sustainable Clothing Ecosystem

![Revastra Logo](https://img.shields.io/badge/Revastra-v1.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![React](https://img.shields.io/badge/React-18+-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-336791)

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Quick Facts](#quick-facts)
- [Problem Statement](#problem-statement)
- [Objectives](#objectives)
- [Core Modules](#core-modules)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Team Members](#team-members)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Git Workflow](#git-workflow)
- [Sprint Timeline](#sprint-timeline)
- [Testing](#testing)
- [Evaluation Criteria](#evaluation-criteria)
- [Unique Selling Points](#unique-selling-points)
- [Project Structure](#project-structure)
- [Contributing Guidelines](#contributing-guidelines)
- [Contact & Support](#contact--support)

---

## 🎯 Project Overview

**Revastra** is a multi-service, sustainable clothing ecosystem built on a **microservice architecture**. It brings together laundry, recycling, donation, stitching, and upcycling into a single unified platform — promoting a circular clothing lifecycle while creating economic opportunities for skilled workers.

### Vision
Transform the clothing industry by creating a sustainable, trustworthy, and economically empowering ecosystem where laundry services, donations, recycling, stitching, and upcycling are seamlessly integrated into one platform.

### Mission
Reduce textile waste through circular fashion practices while enabling skilled workers, students, and homemakers to earn income by providing quality clothing services.

---

## 📊 Quick Facts

| Field | Details |
|-------|---------|
| **Project Name** | Revastra |
| **Version** | v1.0 |
| **Sprint Duration** | 7 Days (3-9 April 2026) |
| **Evaluation Date** | 10 April 2026 |
| **Program** | GlobalLogic Java Track TE 2.0 |
| **Team Size** | 4 Members |
| **Repository** | `gl-spark-revastra` |
| **Type** | Baselined End-to-End Delivery |

---

## 🔴 Problem Statement

The current clothing service landscape suffers from critical gaps:

1. **Rapid textile waste** - Fast fashion increases waste; lack of sustainable alternatives
2. **Fragmented services** - No trusted, unified platform for laundry, tailoring, recycling
3. **Worker exploitation** - Skilled workers have no dedicated platform to monetize abilities
4. **Safety concerns** - Lack of verification when booking in-home workers
5. **No incentive mechanism** - No rewards for clothing donation or recycling

---

## ✅ Objectives

- ✓ Build a complete end-to-end clothing services ecosystem under one platform
- ✓ Promote sustainable fashion through reuse, recycling, and upcycling
- ✓ Create earning opportunities for skilled workers, students, and homemakers
- ✓ Ensure worker trust through phone and admin verification with badge system
- ✓ Enable seamless booking, payment, and reward redemption

---

## 📦 Core Modules

| # | Module | Features | Owner |
|---|--------|----------|-------|
| 1 | **Laundry Management** | Washing, dry cleaning, ironing, pickup & delivery | Sachin |
| 2 | **Donation & Recycling** | Donate clothes, earn reward points, recycling requests | Rakshita |
| 3 | **Stitching & Alteration** | Minor fitting services, worker booking, alteration orders | Samridhi |
| 4 | **Upcycle Marketplace** | Worker skill registration, product listing, earnings | Samridhi |
| 5 | **Payment & Wallet** | Online payment gateway, wallet top-up, discounts via points | Rakshita |
| 6 | **Reward System** | Points earned on donation, redeemable for discounts | Rakshita |
| 7 | **Worker Verification** | Phone OTP, admin approval workflow, verified badge display | Samridhi |
| 8 | **Ratings & Reviews** | Post-service rating, written review, trust scoring | Tushar |
| 9 | **Admin Dashboard** | User management, worker approval, order monitoring | Tushar |

---

## 🛠️ Technology Stack

### Backend
- **Language**: Java 17+
- **Framework**: Spring Boot 3.2.x
- **Architecture**: Microservices with Spring Cloud
- **API Style**: REST APIs

### Frontend
- **Framework**: React 18+
- **State Management**: React Hooks
- **HTTP Client**: Axios
- **Styling**: CSS3 / Tailwind CSS (recommended)

### Database
- **Primary DB**: PostgreSQL 14+
- **ORM**: JPA/Hibernate
- **Migrations**: Flyway or Liquibase (recommended)

### Authentication & Security
- **Auth Framework**: Spring Security
- **Token-based**: JWT with refresh token flow
- **Password Encoding**: BCrypt

### API Gateway & Service Discovery
- **API Gateway**: Spring Cloud Gateway
- **Service Registry**: Netflix Eureka
- **Load Balancing**: Client-side (Ribbon)

### Testing
- **Unit Testing**: JUnit 5
- **Mocking**: Mockito
- **Integration Testing**: Testcontainers (optional)
- **Coverage Target**: Minimum 70%

### DevOps & Build
- **Build Tool**: Maven 3.8+
- **Version Control**: Git / GitHub
- **CI/CD Ready**: GitHub Actions compatible
- **Container Ready**: Docker-compatible

---

## 🏗️ Architecture

### Microservices Architecture Flow

```
┌─────────────────────────────────────────────────┐
│                  User/Browser                    │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
        ┌─────────────────────┐
        │   API Gateway       │
        │ (Spring Cloud)      │
        │ Auth, Rate Limit    │
        └────────┬────────────┘
                 │
    ┌────────────┼────────────────────┐
    │            │                    │
    ▼            ▼                    ▼
┌──────────┐  ┌──────────┐     ┌──────────────┐
│   User   │  │ Laundry  │     │  Recycling   │
│ Service  │  │ Service  │     │   Service    │
└────┬─────┘  └────┬─────┘     └──────┬───────┘
     │             │                   │
     │   Service Registry (Eureka)     │
     │      (Dynamic Discovery)         │
     │                                  │
    ▼             ▼                    ▼
┌──────────┐  ┌──────────┐     ┌──────────────┐
│ Upcycle  │  │ Order    │     │   Payment    │
│ Service  │  │ Service  │     │   Service    │
└──────────┘  └──────────┘     └──────────────┘
     │             │                   │
     └─────────────┼───────────────────┘
                   │
                   ▼
           ┌──────────────────┐
           │   PostgreSQL DB  │
           │  (Per Service)   │
           └──────────────────┘
```

### Mandatory Components

| Component | Description | Status |
|-----------|-------------|--------|
| **API Gateway** | Single entry point, routing, auth filter, rate limiting | MANDATORY |
| **Service Registry** | Eureka-based service registration and discovery | MANDATORY |
| **User Service** | Registration, login, JWT token issue & refresh | MANDATORY |
| **Laundry Service** | Booking, pricing, pickup/delivery scheduling | MANDATORY |
| **Recycling Service** | Donation intake, reward point calculation | MANDATORY |
| **Upcycle Service** | Worker listing, skill registration, marketplace | MANDATORY |
| **Order Service** | Order lifecycle management, status tracking | MANDATORY |
| **Payment Service** | Payment gateway integration, wallet management | MANDATORY |
| **Notification Service** | Email/SMS for booking, donation, verification | GOOD TO HAVE |

### Request Flow

```
User Login → Browse Services → Select Service & Book → 
API Gateway → Appropriate Microservice → Worker Assignment → 
Payment Processing → Order Status Tracking → Service Completion → 
Rating & Reward Points → Order Complete
```

---

## 👥 Team Members

| Name | Role | Backend Ownership | Frontend Ownership | GitHub |
|------|------|-------------------|-------------------|--------|
| **Rakshita** | Core Logic Lead | Recycling Service, Payment Service, Reward System | Donation UI, Wallet UI, Points Dashboard | @rakshita |
| **Sachin** | Order & Laundry Lead | Laundry Service, Order Service | Laundry Booking UI, Order Tracking UI | @sachin |
| **Samridhi** | Marketplace Lead | Upcycle Service, Worker Verification | Worker Listing, Stitching & Alteration UI | @samridhi |
| **Tushar** | Integration & UI Lead | User Service, API Gateway, Admin Endpoints | Main Dashboard, Navigation, Ratings UI | @tushar |

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

### System Requirements
- **Java 17 or higher** - [Download](https://www.oracle.com/java/technologies/downloads/#java17)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Git** - [Download](https://git-scm.com/)
- **Node.js 16+** - [Download](https://nodejs.org/)
- **npm or yarn** - Comes with Node.js

### Databases & Services
- **PostgreSQL 14+** - [Download](https://www.postgresql.org/download/)
- Create a database named `revastra`

### Verify Installation
```bash
java -version        # Java 17+
mvn -version        # Maven 3.8+
git --version       # Git installed
node -v             # Node.js v16+
npm -v              # npm installed
```

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-org/gl-spark-revastra.git
cd gl-spark-revastra
```

### 2. Create Database

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE revastra;
CREATE USER revastra_user WITH PASSWORD 'secure_password';
ALTER ROLE revastra_user SET client_encoding TO 'utf8';
ALTER ROLE revastra_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE revastra_user SET default_transaction_deferrable TO on;
ALTER ROLE revastra_user SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE revastra TO revastra_user;
\q
```

### 3. Configure Properties

Update `application.properties` in each service:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/revastra
spring.datasource.username=revastra_user
spring.datasource.password=secure_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Eureka Server Configuration (for each service)
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

# Spring Cloud Config
spring.application.name=your-service-name
server.port=8081
```

### 4. Build Backend Services

```bash
# Build all modules
mvn clean install -DskipTests=true

# Build specific service
cd laundry-service
mvn clean package
```

### 5. Setup Frontend

```bash
cd frontend

# Install dependencies
npm install

# Optional: Install additional packages if needed
npm install axios react-router-dom

# Build for production
npm run build
```

---

## ▶️ Running the Application

### Option 1: Run All Services (Development)

```bash
# Terminal 1 - Service Registry (Eureka)
cd service-registry
mvn spring-boot:run

# Terminal 2 - API Gateway
cd api-gateway
mvn spring-boot:run

# Terminal 3 - User Service
cd user-service
mvn spring-boot:run

# Terminal 4 - Laundry Service
cd laundry-service
mvn spring-boot:run

# Terminal 5 - Recycling Service
cd recycling-service
mvn spring-boot:run

# Terminal 6 - Upcycle Service
cd upcycle-service
mvn spring-boot:run

# Terminal 7 - Order Service
cd order-service
mvn spring-boot:run

# Terminal 8 - Payment Service
cd payment-service
mvn spring-boot:run

# Terminal 9 - Frontend (React)
cd frontend
npm start
```

### Option 2: Using Docker (Future Enhancement)

```bash
# Build Docker images
docker-compose build

# Start all services
docker-compose up
```

### Verify Services are Running

```bash
# Check Eureka Dashboard
curl http://localhost:8761/

# Check API Gateway
curl http://localhost:8080/api/health

# Check User Service
curl http://localhost:8081/api/users/health
```

### Access the Application

- **Frontend**: http://localhost:3000
- **API Gateway**: http://localhost:8080
- **Eureka Dashboard**: http://localhost:8761

---

## 🔌 API Endpoints

### Authentication
```
POST   /api/users/register          Register a new user
POST   /api/users/login             Authenticate and get JWT (public)
POST   /api/users/refresh           Refresh JWT token
```

### Laundry Services
```
POST   /api/laundry/book            Book a laundry service
GET    /api/laundry/slots           Get available pickup slots
GET    /api/laundry/{id}            Get laundry booking details
PUT    /api/laundry/{id}/cancel     Cancel laundry booking
```

### Donation & Recycling
```
POST   /api/recycling/donate        Submit a clothing donation
GET    /api/recycling/points        Get user reward points
GET    /api/recycling/history       Get donation history
```

### Worker & Upcycle
```
GET    /api/workers                 List verified workers
GET    /api/workers/{id}            Get worker details
POST   /api/workers/register        Worker self-registration
POST   /api/workers/{id}/verify     Verify worker phone (OTP)
GET    /api/marketplace/products    List upcycled products
```

### Orders
```
POST   /api/orders                  Create a new service order
GET    /api/orders/{id}             Get order details
GET    /api/orders                  Get user's orders
PUT    /api/orders/{id}/status      Update order status
```

### Payments
```
POST   /api/payment/pay             Process payment
GET    /api/payment/wallet          Get wallet balance
POST   /api/payment/wallet/topup    Add funds to wallet
GET    /api/payment/wallet/history  Get payment history
```

### Ratings & Reviews
```
POST   /api/reviews                 Submit a review
GET    /api/reviews/order/{id}      Get reviews for order
GET    /api/workers/{id}/ratings    Get worker rating
```

### Admin
```
GET    /api/admin/workers/pending   List pending worker approvals
PUT    /api/admin/workers/{id}/approve   Approve worker
GET    /api/admin/orders            Get all orders (monitoring)
GET    /api/admin/users             Get all users
```

---

## 📊 Database Schema

### Core Entities

#### User
```
- id (PK)
- name
- email (Unique)
- password_hash
- phone
- role (USER, WORKER, ADMIN)
- address
- wallet_balance
- created_at
- updated_at
```

#### Worker
```
- id (PK)
- user_id (FK)
- skills
- experience_years
- rating (0-5)
- badge_status
- verification_status (PENDING, VERIFIED, REJECTED)
- phone_verified
- created_at
- updated_at
```

#### Order
```
- id (PK)
- user_id (FK)
- worker_id (FK)
- service_type (LAUNDRY, STITCHING, UPCYCLE)
- status (PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED)
- total_amount
- created_at
- completed_at
```

#### Payment
```
- id (PK)
- order_id (FK)
- user_id (FK)
- amount
- status (PENDING, SUCCESS, FAILED, REFUNDED)
- payment_method (CARD, WALLET, UPI)
- transaction_id
- created_at
```

#### Reward
```
- id (PK)
- user_id (FK)
- points_earned
- source (DONATION, REFERRAL, PURCHASE)
- points_redeemed
- balance
- last_updated
```

#### Donation
```
- id (PK)
- user_id (FK)
- item_count
- description
- status (PENDING, ACCEPTED, COLLECTED)
- points_earned
- created_at
```

#### Review
```
- id (PK)
- order_id (FK)
- rating (1-5)
- comment
- created_at
```

---

## 📚 Git Workflow

### Repository Setup

```bash
# Add team members as collaborators
git remote add origin https://github.com/your-org/gl-spark-revastra.git

# Add GL evaluator as read-only collaborator before 2 April EOD
```

### Branching Strategy

| Branch | Purpose | Who Merges |
|--------|---------|-----------|
| `main` | Production-ready, stable code only | Team Lead via PR |
| `develop` | Integration branch for all features | Team Lead |
| `feature/<story-id>` | One branch per user story (e.g., feature/US-001) | Developer via PR |
| `hotfix/<issue>` | Critical bug fixes only | Team Lead |

### Commit Message Convention

```
feat(US-002): add laundry booking endpoint
fix(auth): resolve JWT expiry issue
test(US-003): add JUnit tests for recycling service
docs: update README with run steps
chore: upgrade Spring Boot to 3.2.4
refactor: extract WorkerValidator class
```

### Git Workflow Example

```bash
# 1. Create feature branch
git checkout develop
git pull origin develop
git checkout -b feature/US-001

# 2. Make changes and commit
git add .
git commit -m "feat(US-001): implement user registration"

# 3. Push and create Pull Request
git push origin feature/US-001

# 4. After review and approval, merge via PR
# Pull Request → Reviewed → Approved → Merged to develop
# Then merge develop to main for release

# 5. Clean up
git branch -d feature/US-001
git push origin --delete feature/US-001
```

### ⚠️ Important Rules

- ✗ **Never commit directly to main**
- ✓ All changes must go through Pull Requests
- ✓ At least one other team member must review
- ✓ All tests must pass before merge
- ✓ No secrets or sensitive data in commits

---

## 📅 Sprint Timeline

| Day | Date | Milestone | Owner |
|-----|------|-----------|-------|
| 1 | 3 Apr | Project kickoff, PRD finalized, repo created, design slides | All |
| 2 | 4 Apr | Microservice scaffolding, DB schema, API contracts | All |
| 3-4 | 5-6 Apr | Backend service implementation, endpoints coded & tested | All |
| 5-6 | 7-8 Apr | Frontend UI built, connected to backend via API Gateway | All |
| 7 | 9 Apr | End-to-end testing, bug fixes, code freeze, final push | All |
| **Eval** | **10 Apr** | **Live demo - all features functional, no crashes** | **All** |

---

## 🧪 Testing

### Unit Testing

```bash
# Run all unit tests
mvn test

# Run tests for specific module
mvn test -f laundry-service/pom.xml

# Run with coverage report
mvn test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

### Test Coverage Target

- **Minimum Coverage**: 70%
- **Target Coverage**: 85%+
- **Critical Path**: 100% coverage for core business logic

### Example Unit Test

```java
@SpringBootTest
class UserServiceTest {
    
    @InjectMocks
    private UserService userService;
    
    @Mock
    private UserRepository userRepository;
    
    @Test
    void testRegisterUserSuccess() {
        // Arrange
        UserRegistrationRequest request = new UserRegistrationRequest(
            "user@example.com", "password123"
        );
        
        // Act
        UserResponse response = userService.register(request);
        
        // Assert
        assertNotNull(response.getId());
        assertEquals("user@example.com", response.getEmail());
        verify(userRepository, times(1)).save(any());
    }
}
```

### Integration Testing

```bash
# Run integration tests only
mvn test -Dgroups=integration

# Run with Testcontainers
mvn test -Duse.testcontainers=true
```

---

## ✅ Evaluation Criteria

| # | Evaluation Area | Weight | Key Expectations |
|---|-----------------|--------|------------------|
| 1 | **Working Code Demo** | 25% | All features functional, no crashes during demo |
| 2 | **Architecture & Design** | 20% | Microservices, API gateway, clear service separation |
| 3 | **User Stories & PRD** | 15% | 6+ stories with acceptance criteria and traceability |
| 4 | **Testing (TDD/JUnit)** | 15% | Test-first approach, 70%+ coverage, Mockito usage |
| 5 | **Code Quality & CVS** | 10% | Clean commits, branching strategy, no secrets |
| 6 | **Diagrams & Slides** | 10% | All 7 diagram types complete and accurate |
| 7 | **Team Collaboration** | 5% | Equal commits, PR history, peer code reviews |

---

## 🌟 Unique Selling Points

| USP | How Revastra Delivers |
|-----|----------------------|
| **Circular Clothing Ecosystem** | One app for laundry, donation, recycling, stitching, & upcycling |
| **Verified, Trusted Workers** | Phone OTP + admin approval + visible verified badge |
| **Reward-Based Sustainability** | Earn points for every donation, redeem at checkout |
| **Employment Generation** | Skilled workers, students, homemakers earn through marketplace |
| **Multi-Service Platform** | No need for separate apps - all clothing needs in one place |

---

## 📁 Project Structure

```
gl-spark-revastra/
├── service-registry/                 # Eureka Service Registry
│   ├── src/
│   │   ├── main/java/...
│   │   └── main/resources/
│   └── pom.xml
│
├── api-gateway/                      # Spring Cloud Gateway
│   ├── src/
│   │   ├── main/java/...
│   │   └── main/resources/
│   └── pom.xml
│
├── user-service/                     # User Management Service
│   ├── src/
│   │   ├── main/java/com/revastra/user/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   └── dto/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── laundry-service/                  # Laundry Service
│   ├── src/
│   │   ├── main/java/com/revastra/laundry/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── recycling-service/                # Recycling & Donation Service
│   ├── src/
│   │   ├── main/java/com/revastra/recycling/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── upcycle-service/                  # Upcycle Marketplace Service
│   ├── src/
│   │   ├── main/java/com/revastra/upcycle/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── order-service/                    # Order Management Service
│   ├── src/
│   │   ├── main/java/com/revastra/order/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── payment-service/                  # Payment & Wallet Service
│   ├── src/
│   │   ├── main/java/com/revastra/payment/
│   │   ├── test/
│   │   └── main/resources/
│   └── pom.xml
│
├── frontend/                         # React Frontend
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   │   ├── Auth/
│   │   │   ├── Laundry/
│   │   │   ├── Donation/
│   │   │   ├── Worker/
│   │   │   └── Admin/
│   │   ├── services/
│   │   │   └── api.js
│   │   ├── pages/
│   │   ├── styles/
│   │   ├── App.js
│   │   └── index.js
│   ├── package.json
│   └── .env.example
│
├── common/                           # Shared utilities & constants
│   ├── src/
│   │   ├── main/java/com/revastra/common/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── util/
│   │   │   └── constants/
│   │   └── test/
│   └── pom.xml
│
├── docs/                             # Documentation
│   ├── API_DOCUMENTATION.md
│   ├── DATABASE_SCHEMA.md
│   ├── ARCHITECTURE.md
│   ├── USER_STORIES.md
│   └── SETUP_GUIDE.md
│
├── postman/                          # Postman Collection
│   └── Revastra_API.postman_collection.json
│
├── pom.xml                           # Parent POM
├── README.md                         # This file
├── .gitignore
├── docker-compose.yml                # Docker setup (optional)
└── .github/
    └── workflows/
        └── ci.yml                    # CI/CD Pipeline
```

---

## 🤝 Contributing Guidelines

### Before You Start

1. **Create a feature branch** from `develop`:
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/US-XXX
   ```

2. **Follow code conventions**:
   - Use meaningful variable names
   - Write self-documenting code
   - Add comments for complex logic
   - Follow Java naming conventions (camelCase)

3. **Write tests first** (TDD):
   - Write unit tests before implementation
   - Aim for 70%+ code coverage
   - Test both success and failure scenarios

4. **Make small, atomic commits**:
   ```bash
   git commit -m "type(scope): description"
   ```

5. **Push to your branch**:
   ```bash
   git push origin feature/US-XXX
   ```

6. **Create a Pull Request**:
   - Write a clear PR description
   - Link related issues
   - Request review from at least one team member
   - Ensure all CI checks pass

7. **Address review comments**:
   - Make requested changes
   - Push updates to the same branch
   - Don't force push without discussion

8. **Merge only after approval**:
   - All tests must pass
   - At least one approval required
   - No merge conflicts
   - Use "Squash and merge" or "Create merge commit"

---

## 📖 User Stories

### US-001: User Registration & Login
**As a** new visitor to the Revastra platform  
**I want to** register using email and password, then log in to access services  
**So that** I can book laundry, donate clothes, and track my orders

**Acceptance Criteria:**
1. Given valid email and password, when I submit registration, then my account is created and a JWT is returned
2. Given an already-registered email, when I try to register again, then the system returns a 409 Conflict error
3. Given invalid credentials, when I attempt login, then I receive a 401 Unauthorized response

### US-002: Book a Laundry Service
**As a** registered user  
**I want to** select a laundry service type, choose a pickup slot, and confirm booking  
**So that** my clothes are collected, cleaned, and delivered without hassle

**Acceptance Criteria:**
1. Given a logged-in user with valid address, when they submit a laundry booking, then an order is created with status PENDING
2. Given a selected time slot, when the booking is confirmed, then the user receives a booking confirmation notification
3. Given an invalid or past pickup time, when the user submits, then a validation error is returned

### US-003: Donate Clothes & Earn Reward Points
**As a** registered user with clothes to donate  
**I want to** submit a donation request with item details and earn reward points  
**So that** my unused clothes are recycled sustainably and I get points redeemable for discounts

**Acceptance Criteria:**
1. Given a submitted donation request, when it is received, then reward points are credited to user's wallet
2. Given reward points in the wallet, when the user places an order, then points are applied as discount
3. Given a donation with invalid item details, when submitted, then the system shows a validation error

### US-004: Worker Registers & Gets Verified
**As a** skilled worker (tailor, craftsperson)  
**I want to** register with skills and verify my phone number so I appear in the marketplace  
**So that** I can receive bookings and earn income through the platform

**Acceptance Criteria:**
1. Given a valid phone number, when OTP is entered correctly, then phone verification is marked complete
2. Given a verified phone, when admin approves the worker profile, then the Verified badge is displayed on the listing
3. Given an unverified or pending worker, when a user searches for workers, then the unverified worker does not appear in results

### US-005: Book Stitching or Alteration Service
**As a** registered user  
**I want to** browse verified tailors and book a stitching or alteration appointment  
**So that** I can get my clothes altered by a trusted, verified worker

**Acceptance Criteria:**
1. Given a list of verified workers, when user selects one and submits booking, then an order is created
2. Given a booking, when the worker accepts, then booking status updates to CONFIRMED
3. Given an unverified worker, when user attempts to book them, then system blocks the booking

### US-006: Make Payment & Use Wallet
**As a** registered user completing an order  
**I want to** pay for services online and use wallet balance or reward points at checkout  
**So that** I have a flexible, secure payment experience with discount options

**Acceptance Criteria:**
1. Given a confirmed order, when user initiates payment, then payment is processed and status updates to PAID
2. Given wallet balance, when selected at checkout, then balance is deducted from order total
3. Given a failed payment, when retried, then previous failed transaction is voided and new one is created

---

## 📞 Contact & Support

### Team Contacts
- **Rakshita** (Core Logic Lead) - rakshitasuyal900@gmail.com | @rakshitasuyal (GitHub)
- **Sachin** (Order & Laundry Lead) - vishwakarmasachin632@gmail.com | @vishwakarmasachin632 (GitHub)
- **Samridhi** (Marketplace Lead) - sinu.secu@gmail.com | @Samriddhig19 (GitHub)
- **Tushar** (Integration & UI Lead) - tgoyal2001@gmail.com | @tushargoyal11 (GitHub)

### Resources
- 📘 [Project Documentation](./docs)
- 🔌 [API Documentation](./docs/API_DOCUMENTATION.md)
- 🗄️ [Database Schema](./docs/DATABASE_SCHEMA.md)
- 🏗️ [Architecture Guide](./docs/ARCHITECTURE.md)
- 📝 [User Stories](./docs/revastra-user-stories.md)

### Important Dates
- **Sprint Start**: 3 April 2026
- **Sprint End**: 9 April 2026
- **Evaluation Date**: 10 April 2026
- **Code Freeze**: 9 April 2026, 5:00 PM

---

## ⚠️ Important Reminders

1. ✅ **All features must be functional** on Evaluation Day (10 April 2026)
2. ✅ **Minimum 70% test coverage** required for all services
3. ✅ **No direct commits to main** - Use feature branches and PRs
4. ✅ **All team members must have visible Git contributions**
5. ✅ **Every module must be traceable** to a user story
6. ✅ **Database migrations** must be automated and version-controlled
7. ✅ **Sensitive data** (passwords, API keys, DB credentials) must NEVER be committed
8. ✅ **Code should be peer-reviewed** before merging to develop
9. ✅ **Documentation** should be updated with code changes
10. ✅ **Bring laptops charged** - Live demo expected on Evaluation Day

---

## 📄 License

This project is proprietary to GlobalLogic Java Track TE 2.0 Program.

---

## 🙌 Acknowledgments

This project is developed as part of the GlobalLogic Java Track TE 2.0 Program, focusing on creating a sustainable solution for the clothing industry while generating employment opportunities.

---

**Last Updated**: April 2026  
**Version**: v1.0  
**Status**: Development
