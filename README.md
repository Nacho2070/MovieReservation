# App Endpoint Instructions

## Technologies Used
- Java with Spring boot
- Docker
- Spring Security for authentication and authorization using JWT
- Unit and Integration Testing with Mockito and JUnit
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




