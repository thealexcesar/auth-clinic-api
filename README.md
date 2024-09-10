# Auth-Clinic 🏥

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

## Project Overview

**Auth-Clinic** is a Spring Boot application designed to handle authentication and authorization for a clinical system. The app provides a secure way to manage different user roles, such as **admin**, **doctor**, **receptionist**, and **patient**, ensuring that access to clinical data is properly controlled based on the user's role.

The application uses **JWT (JSON Web Tokens)** for secure authentication and **PostgreSQL** as the database.

### Key Features
- **User Registration**: Allows new users to register with specific roles.
- **Login**: Users can log in and obtain a JWT token.
- **Logout**: Invalidates the user's session.
- **Role-based Access Control**: Controls access to certain endpoints based on the user's role (admin, doctor, receptionist, patient).
- **REST API**: Provides endpoints for registration, login, logout, and data queries.

### Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Backend framework for creating the application.
- **Spring Security**: Used for authentication and authorization.
- **JWT (JSON Web Tokens)**: Token-based authentication system.
- **PostgreSQL**: Database used to store user data and role information.
- **Hibernate**: ORM framework for database interactions.
- **Maven**: Build tool and dependency management.

### Installation

To run the application locally, follow these steps:

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/thealexcesar/auth-clinic.git
    ```

2. **Configure PostgreSQL**:
    - Make sure you have PostgreSQL installed and running.
    - Create a database called `auth_clinic`.
    - Update the `application.properties` or `application.yml` file with your PostgreSQL credentials.

3. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

4. **Access the API**:
    Once the application is running, you can access the API at:
    ```
    http://localhost:8080
    ```

## API Endpoints

### Authentication

- **POST /auth/register**
    - **Description**: Registers a new user in the system.
    - **Request Body**:
      <details>
      <summary>Example Request Body</summary>
      ```json
      {
        "username": "user",
        "password": "password",
        "role": "ADMIN"
      }
      ```
      </details>
    - **Response**:
        - **Status Code**: 201 Created
        - **Body**:
          <details>
          <summary>Example Response Body</summary>
          ```json
          {
            "id": 1,
            "username": "user",
            "role": "ADMIN"
          }
          ```
          </details>
        - **Headers**:
          ```
          Location: /auth/users/{userId}
          ```

- **POST /auth/login**
    - **Description**: Logs in the user and returns a JWT token for authentication.
    - **Request Body**:
      <details>
      <summary>Example Request Body</summary>
      ```json
      {
        "username": "user",
        "password": "password"
      }
      ```
      </details>
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "token": "jwt_token"
      }
      ```
      </details>

- **POST /auth/logout**
    - **Description**: Logs out the user.
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "message": "Logout successful"
      }
      ```
      </details>

### Consultations

- **POST /consultations**
    - **Description**: Creates a new consultation. Requires ADMIN, DOCTOR, or RECEPTIONIST permissions.
    - **Request Body**:
      <details>
      <summary>Example Request Body</summary>
      ```json
      {
        "date": "2024-09-10",
        "diagnosis": "diagnosis",
        "durationMinutes": 30,
        "isEmergency": false,
        "reason": "consultation reason",
        "doctor": {
          "id": 1
        },
        "patient": {
          "id": 2
        },
        "symptoms": ["FEVER", "COUGH"]
      }
      ```
      </details>
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "id": 1,
        "date": "2024-09-10",
        "diagnosis": "diagnosis",
        "durationMinutes": 30,
        "isEmergency": false,
        "reason": "consultation reason",
        "doctor": {
          "id": 1
        },
        "patient": {
          "id": 2
        },
        "symptoms": ["FEVER", "COUGH"]
      }
      ```
      </details>

- **PUT /consultations/{id}**
    - **Description**: Updates an existing consultation. Requires ADMIN, DOCTOR, or RECEPTIONIST permissions.
    - **Request Body**:
      <details>
      <summary>Example Request Body</summary>
      ```json
      {
        "date": "2024-09-10",
        "diagnosis": "updated diagnosis",
        "durationMinutes": 45,
        "isEmergency": true,
        "reason": "updated reason",
        "doctor": {
          "id": 1
        },
        "patient": {
          "id": 2
        },
        "symptoms": ["HEADACHE"]
      }
      ```
      </details>
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "id": 1,
        "date": "2024-09-10",
        "diagnosis": "updated diagnosis",
        "durationMinutes": 45,
        "isEmergency": true,
        "reason": "updated reason",
        "doctor": {
          "id": 1
        },
        "patient": {
          "id": 2
        },
        "symptoms": ["HEADACHE"]
      }
      ```
      </details>

- **GET /consultations/{id}**
    - **Description**: Retrieves a specific consultation by ID. Requires ADMIN, DOCTOR, or PATIENT permissions.
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "id": 1,
        "date": "2024-09-10",
        "diagnosis": "diagnosis",
        "durationMinutes": 30,
        "isEmergency": false,
        "reason": "consultation reason",
        "doctor": {
          "id": 1
        },
        "patient": {
          "id": 2
        },
        "symptoms": ["FEVER", "COUGH"]
      }
      ```
      </details>

- **DELETE /consultations/{id}**
    - **Description**: Deletes a specific consultation by ID. Requires ADMIN permissions.
    - **Response**:
      <details>
      <summary>Example Response Body</summary>
      ```json
      {
        "message": "Consultation deleted successfully"
      }
      ```
      </details>

## Testing with Postman

1. **Environment Setup**:
    - Create a new environment in Postman to store variables such as `baseUrl` and `token`.

2. **Authentication**:
    - **Request**: `POST /auth/login`
    - **Body**: Enter user credentials to obtain the JWT token.
    - **Save Token**: Store the token in the `token` environment variable.

3. **Protected Endpoints**:
    - Use the stored token in the `Authorization` header as `Bearer {token}` to access protected endpoints.

4. **Testing Consultations**:
    - **Create Consultation**: `POST /consultations`
    - **Update Consultation**: `PUT /consultations/{id}`
    - **Retrieve Consultation**: `GET /consultations/{id}`
    - **Delete Consultation**: `DELETE /consultations/{id}`


### Database Configuration

The application uses PostgreSQL as the primary database. Make sure you have PostgreSQL installed and running locally or on a remote server. Update the `application.properties` file with your database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/auth_clinic
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
