# LevelNine - Spring Boot Learning Journey

## 🎯 About This Project

This project represents my **Level Nine** journey in Spring Boot development, inspired by the LOTM (Lord of the Mysteries) series progression system.

**Level Nine = Beginner/Foundation Level**

I'm documenting my learning path from basics to advanced Spring Boot concepts, with each level representing increased mastery.

---

## 📚 What I'm Building

A **Production-Grade Blog Application** solving real-world problems:

### Core Features:
- **User Management** - Registration, Login, CRUD with BCrypt encryption
- **Article Management** - Full CRUD with pagination and authorization
- **Nested Comment System** - Reddit/YouTube-style threaded discussions (see below)
- **Like/Unlike Features** - Social engagement (coming soon)
- **JWT Authentication** - Secure token-based auth

### 🌟 Featured: Nested Comment System

**Real-world implementation** of threaded comments like Reddit, YouTube, and Quora:

**Key Features:**
- ✅ **Unlimited Nesting** with depth limit (max 5 levels)
- ✅ **Lazy Loading** - Load replies on demand for performance
- ✅ **Soft Delete** - Preserves conversation threads
- ✅ **Authorization** - Users can only edit/delete their own comments
- ✅ **Pagination** - Handles thousands of comments efficiently
- ✅ **Security** - Cross-article reply prevention

**Technical Highlights:**
- Self-referencing JPA relationships (`@ManyToOne` to itself)
- Recursive DTO structures for nested responses
- Depth validation and calculation algorithms
- Guard clauses for clean validation logic
- Repository query methods with Spring Data JPA

**Example Structure:**
```
Article: "Spring Boot Tutorial"
  ├─ Comment 1: "Great article!" (depth 0)
  │   ├─ Reply: "I agree!" (depth 1)
  │   │   └─ Reply: "Me too!" (depth 2)
  │   └─ Reply: "Thanks!" (depth 1)
  └─ Comment 2: "Very helpful" (depth 0)
      └─ Reply: "Agreed!" (depth 1)
```

**Why This Matters:**
This isn't a simple TODO app - it's a **production-ready feature** used by major platforms. Building this taught me:
- Complex database relationships
- Performance optimization (lazy loading, pagination)
- Security best practices (authorization, validation)
- Real-world problem solving 

---

## 🛠️ Tech Stack

- **Framework:** Spring Boot
- **Database:** MySQL
- **Security:** Spring Security/JWT 
- **Build Tool:** Gradle
- **Language:** Java

---

## 🚀 Current Progress

### Completed Features:
✅ **User Management**
  - Registration & Login
  - Password Encryption (BCrypt)
  - Full CRUD Operations
  - JWT Authentication

✅ **Article Management**
  - Create, Read, Update, Delete
  - Pagination (user-specific & public)
  - Authorization checks
  - RESTful API endpoints

✅ **Nested Comment System** ⭐
  - Create comments & nested replies
  - Update own comments
  - Soft delete with thread preservation
  - Get root comments (paginated)
  - Lazy load replies on demand
  - Depth validation (max 5 levels)
  - Reply count tracking
  - Security & authorization

### In Progress:
🔄 **Comment Features**
  - Get replies endpoint
  - Restore deleted comments
  - Comment search

⏳ **Like/Unlike System**
  - Like articles
  - Like comments
  - Upvote/downvote functionality

⏳ **Advanced Features**
  - Comment sorting (by date, popularity)
  - User profiles
  - Article categories/tags  
---

## 💡 Learning Goals & Achievements

### Mastered:
✅ **Spring Boot Fundamentals**
  - Dependency Injection
  - Service Layer Architecture
  - Repository Pattern
  - Exception Handling

✅ **JPA/Hibernate Relationships**
  - Self-referencing entities
  - Bidirectional relationships
  - Lazy vs Eager loading
  - Cascade operations
  - Custom query methods

✅ **Security & Authentication**
  - JWT token generation & validation
  - Password encryption (BCrypt)
  - Authorization checks
  - SecurityContext management

✅ **RESTful API Design**
  - Proper HTTP methods (GET, POST, PUT, DELETE)
  - Status codes (200, 201, 403, 404, 500)
  - Request/Response DTOs
  - Pagination with Spring Data

✅ **Best Practices**
  - Guard clauses for validation
  - Null safety patterns
  - DTO pattern for data transfer
  - Separation of concerns
  - Clean code principles

### Currently Learning:
🔄 Advanced query optimization
🔄 Caching strategies
🔄 Testing (Unit & Integration)

---

## 🎮 LOTM Level System

- **Level 9:**  - The Awakened
- **Level 8:**  - The Architect of Realms
- **Level 7:** -The Weaver of Efficiency
- **Level 6:** -The Master of Distributed Forces
- ... and beyond!

---

## 📝 Notes

**This is NOT just another TODO app!**

While this started as a learning project, I've focused on building **production-ready features** that solve real-world problems:

- ✅ **Nested comments** - Same complexity as Reddit/YouTube
- ✅ **Security** - Proper authorization and validation
- ✅ **Performance** - Pagination and lazy loading
- ✅ **Scalability** - Designed to handle thousands of users

**Code Quality:**
- All methods reviewed and refactored for production readiness
- Security vulnerabilities identified and fixed
- Edge cases handled (null safety, depth limits, authorization)
- Following industry-standard patterns

**What Makes This Different:**
- Real problem-solving, not just following tutorials
- Understanding WHY, not just HOW
- Learning from code reviews and fixing bugs
- Building features used by major platforms

---

## 🤝 Feedback Welcome

If you're reviewing this code and have suggestions, I'd love to learn from you!
