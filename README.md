# Movie Reservation System
### The Movie Reservation Service is a backend system designed to provide a seamless experience for managing movie showtimes and seat reservations. It enables users to:
## Features
- **User Authentication**
   - Secure sign-up and login system.
   - Role-based access for admins and regular users.

- **Movie and Showtime Management**
   - Admins can add, update, or delete movies and showtimes.
   - Movies are categorized by genres and include descriptions and poster images.

- **Seat Reservation**
   - Users can browse available movies and reserve specific seats for showtimes.
   - Users can view and cancel their reservations.

- **Reporting Tools**
   - Admins can monitor reservations, capacity, and revenue

## SwaggerDoc Link:
### https://movie-reservation-combined-v1.onrender.com/swagger-ui/index.html#
## Local: 
### http://localhost:8080/swagger-ui/index.html#
## Technologies Used
- Java with Spring boot
- Docker to containerize the application and SQL database
- Spring Security for authentication and authorization using JWT
- Unit and Integration Testing with Mockito and JUnit
- The application is located in render
---

#### Note: Only the Auth endpoints are available to unauthenticated users, access to others requires a token

### Auth endpoints
  This controller handles user authentication and registration requests in the application.  
1. **User Registration**

   - **URL:** `/auth/register`
   - **Method:** `POST`
   - **Description:** Registers a new user in the application.
   - **Request Body (JSON):**
     ```json
     {
       "username": "exampleUser",
       "password": "examplePassword",
       "email": "example@mail.com",
       "firstName": "John",
       "lastName": "Doe"
     }
     ```
   - **Response:** `201 Created` status if the registration is successful.

2. **User Login**

   - **URL:** `/auth/log-in`
   - **Method:** `POST`
   - **Description:** Authenticates a user and returns a token upon successful login.
   - **Request Body (JSON):**
     ```json
     {
       "username": "exampleUser",
       "password": "examplePassword"
     }
     ```
   - **Response:** `200 OK` status with a success message and an authentication token (JWT) if the credentials are valid.
  
### Endpoints for Users, Admins and Developer

The following endpoints are restricted to users with the roles `ROLE_USER`,`ROLE_ADMIN` or `ROLE_DEVELOPER`. Authentication is required.

1. **Ticket Reservation**
   - **URL:** `/ticketReservation/**`
   - **Method:** `POST`
   - **Description:** Allows authenticated users and admins to create a ticket reservation.

2. **Reservation**
   - **URL:** `/reservation/**`
   - **Method:** Various HTTP methods
   - **Description:** Handles reservation-related operations for authenticated users and admins.

### Endpoints for Admin and Developers

The following endpoints are restricted to users with the `ROLE_ADMIN` or `ROLE_DEVELOPER`. Authentication is required.

1. **Movies**
   - **URL:** `/movies/**`
   - **Description:** Allows admins to manage movie-related operations (CRUD).

2. **Show Times**
   - **URL:** `/showTime/**`
   - **Description:** Allows admins to manage showtime operations (CRUD).

3. **Rooms**
   - **URL:** `/room/**`
   - **Description:** Allows admins to manage cinema and room seating.
           
## UML:
![image](https://github.com/user-attachments/assets/22fdd530-7253-4910-ad1f-469c40dd9c0b)




