# Job Application

**Hands‑On Spring Boot job application system:**
- A simple web app to manage job postings, companies and company reviews using Spring Boot, Spring Data JPA, PostgreSQL and Docker.

---

## 🚀 Features

- Manage jobs: Create, retrieve, update, delete job listings
- Manage companies: Create, retrieve, update, delete companies
- Manage reviews: Create, retrieve, update, delete company reviews
- Handle applications: Submit and view applications per job  
- RESTful APIs with JSON payloads
- Docker and Docker Compose setup for easy deployment

---

## 🧱 Tech Stack

- Java 17+  
- Spring Boot (Spring Web, Spring Data JPA)  
- H2 Database (default; switchable to MySQL/PostgreSQL)  
- Docker & Docker Compose
- Maven build system

---

## 📦 Getting Started

### Prerequisites

- Java JDK 17 or higher  
- Maven  
- Docker (optional, if you choose containerized setup)
---
### 🔧 Configuration

1. Clone the repo:
   git clone https://github.com/Kunjshah20/Job-Application.git
   cd Job-Application

2. Build & run locally:
   mvn clean install
   mvn spring-boot:run

3. Or start with Docker:
   docker-compose up --build

**4. Application Usage**

Example API Endpoints:
- Default setup on http://localhost:8080:

### 📘 Job Endpoints

| Method | Endpoint       | Description                      |
|--------|----------------|----------------------------------|
| GET    | `/jobs`        | List all job postings            |
| POST   | `/jobs`        | Create a new job posting (JSON)  |
| GET    | `/jobs/{id}`   | Retrieve a job by its ID         |
| PUT    | `/jobs/{id}`   | Update a job by its ID           |
| DELETE | `/jobs/{id}`   | Delete a job by its ID           |
| DELETE | `/jobs`        | Delete all jobs                  |

**Find All Endpoints at the following URL: https://web.postman.co/workspace/My-Workspace~f96fdef2-f618-4599-8163-49e3b4216e2c/collection/23158640-1bbb0be1-27bb-4af8-a793-87bbeec87eed?action=share&source=copy-link&creator=23158640**

---
### Sample payload to create a job: 
### 📥 Create Job – Example Request (POST `/jobs`)

```json
{
  "title": "Project Manager",
  "description": "Oversee project timelines, budgets, and deliverables across teams.",
  "minSalary": "70000",
  "maxSalary": "110000",
  "location": "Chicago, IL",
  "company": {
    "companySeq": 1
  }
}
```
---
## 🛠️ Development

- Configure your database in `src/main/resources/application.properties` to suit your local or production environment.
- Implement business logic in the service layer:  
  `src/main/java/.../service`
- For a faster development experience, consider adding **Spring Boot DevTools** as a development-only dependency.

---

## 🚢 Deployment

- **Docker**:  
  - Use `docker-compose.yaml` to spin up the application along with the database.
  - docker-compose up --build

## 📫 Contact
For questions, suggestions, or collaborations, feel free to reach out:

- Name: Kunj Shah
- GitHub: @Kunjshah20
- Email: shahkunj983@gmail.com
