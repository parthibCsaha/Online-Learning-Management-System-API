# Online Learning Management System (LMS) API

This project is a RESTful backend for an Online Learning Management System built with Spring Boot, Spring Data JPA, Spring Security with JWT, and PostgreSQL. It features role-based authentication (STUDENT and ADMIN) for managing courses, enrollments, lessons, assignments, submissions, and certificate generation.

## Features

- **User Authentication:**
  - **Registration & Login:** Users register and log in to obtain a JWT token.
  - **Role-based Access:** New users are assigned the STUDENT role by default; ADMIN-only endpoints are secured.
- **Course Management:**
  - **Public Endpoints:** Students can view available courses and course details.
  - **Admin Endpoints:** Administrators can create, update, and delete courses.
- **Enrollment:**
  - Students can enroll in courses and track their progress.
- **Lesson & Assignment Management:**
  - View lessons for each course.
  - Assignments are listed per course with deadlines.
  - Students can submit assignments.
- **Certificate Generation:**
  - Once enrollment criteria are met, a certificate (stubbed) is issued.
- **Additional Modules:**
  - **Wishlist & Coupons:** (For future extension; see code placeholders.)
  - **Product Reviews:** (Students can review courses and share feedback.)

## Architecture & Entity Relationships

The project is organized into distinct layers for controllers, services, repositories, and domain entities. The following UML diagram (in PlantUML syntax) visualizes the main entity relationships:

### UML Diagram

```plantuml
@startuml
!define Table(name,desc) class name as "desc" << (T,#FFAAAA) >>
!define primaryKey(field) <b>field</b>

Table(User, "User")
User : primaryKey(id)
User : username
User : password
User : email
User : role

Table(Course, "Course")
Course : primaryKey(id)
Course : title
Course : description
Course : instructor
Course : duration
Course : category

Table(Enrollment, "Enrollment")
Enrollment : primaryKey(id)
Enrollment : enrolledDate
Enrollment : progress

Table(Lesson, "Lesson")
Lesson : primaryKey(id)
Lesson : title
Lesson : content
Lesson : lessonOrder

Table(Assignment, "Assignment")
Assignment : primaryKey(id)
Assignment : title
Assignment : description
Assignment : dueDate

Table(Submission, "Submission")
Submission : primaryKey(id)
Submission : submittedAt
Submission : fileUrl
Submission : grade

Table(Certificate, "Certificate")
Certificate : primaryKey(id)
Certificate : certificateUrl
Certificate : issuedDate

' Relationships
User "1" -- "*" Enrollment : enrolls in
User "1" -- "*" Review : writes
Course "1" -- "*" Enrollment : has enrollments
Course "1" -- "*" Lesson : contains
Course "1" -- "*" Assignment : has assignments
Enrollment "1" -- "0..1" Certificate : earns

Assignment "1" -- "*" Submission : receives

@enduml
```

## Technologies Used
 - Java 11+
 - Spring Boot
 - Spring Data JPA
 - Spring Security with JWT
 - PostgreSQL
 - Lombok
 - BCrypt for password hashing
 - Maven
