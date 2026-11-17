# St. Anthony’s Backend

A REST API for the St. Anthony’s Child & Youth Care Centre website. It receives item donation offers and assistance requests from the website forms and stores them in a MySQL database.

Website repository: https://github.com/mamshazi/st-anthonys-website

**Status:** works locally. Admin login and dashboard (to review, approve or reject applications) and online hosting are the next steps.

## Tech
Java, Spring Boot 4, Spring Web, Spring Data JPA (Hibernate), Bean Validation, MySQL 8, Maven

## Endpoints

| Method | Path | Purpose |
|---|---|---|
| POST | `/api/donations` | Submit a donation offer |
| POST | `/api/applications` | Submit an assistance request |

Both return `201 Created` with `{ "id": ..., "status": "NEW" }`. Invalid input returns `400`. The API never sends submitted details back out, to keep applications private.

**Donation example**
```json
{ "name": "Test Donor", "phone": "0123456789", "email": "test@example.com",
  "item": "Blankets", "quantity": 5, "condition": "New", "notes": "" }
```

**Assistance request example**
```json
{ "type": "Individual", "name": "Test Applicant", "phone": "0123456789",
  "email": "", "need": "School uniforms", "circumstances": "Short description" }
```

Statuses: `NEW`, `UNDER_REVIEW`, `APPROVED`, `REJECTED`, `CLOSED`.

## Run it locally

You need Java 21 or newer and MySQL 8.

1. Create the database and a user for the app:
   ```sql
   CREATE DATABASE st_anthonys CHARACTER SET utf8mb4;
   CREATE USER 'stanthonys_app'@'localhost' IDENTIFIED BY 'choose-your-own-password';
   GRANT ALL PRIVILEGES ON st_anthonys.* TO 'stanthonys_app'@'localhost';
   ```
2. Set the password for your terminal session (it is never stored in the code):
   ```powershell
   $env:DB_PASSWORD = "choose-your-own-password"
   ```
3. Start the API:
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```
4. It runs at `http://localhost:8080`. Tables are created automatically.

## Project structure
```
src/main/java/com/stanthonys/backend/
  BackendApplication.java        entry point
  SubmissionController.java      the two POST endpoints and input validation
  Donation.java                  donation entity
  AssistanceRequest.java         assistance request entity
  DonationRepository.java        database access
  AssistanceRequestRepository.java
  Status.java                    status values
  CorsConfig.java                lets the website call the API
src/main/resources/application.properties
```

## Privacy and security notes
- Database password comes from the `DB_PASSWORD` environment variable, not from the code.
- Applications may contain sensitive details, so records are not exposed by any public endpoint.
- Planned: authenticated admin endpoints only, and collection of the minimum information the centre needs.

## Roadmap
- Admin login and dashboard
- Hosting the API and database online
- Automated tests