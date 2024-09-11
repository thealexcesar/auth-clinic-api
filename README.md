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

#### Login

- **Endpoint**: `POST /auth/login`
- **Request Body**:
    ```json
    {
        "username": "user_example",
        "password": "password_example"
    }
    ```
- **Response**:
    ```json
    {
        "token": "jwt_token_example"
    }
    ```

#### Register

- **Endpoint**: `POST /auth/register`
- **Request Body**:
    ```json
    {
        "username": "new_user",
        "password": "new_password",
        "role": "PATIENT"
    }
    ```
- **Response**:
    ```json
    {
        "id": 1,
        "username": "new_user",
        "role": "PATIENT"
    }
    ```

#### Logout

- **Endpoint**: `POST /auth/logout`
- **Response**:
    ```json
    {
        "message": "Logout successful."
    }
    ```

### Medical Appointments

#### List All Appointments

- **Endpoint**: `GET /consultations`
- **Permissions**: `ROLE_ADMIN`, `ROLE_DOCTOR`, `ROLE_PATIENT`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "date": "2024-09-01",
            "diagnosis": "Sinusitis",
            "durationMinutes": 30,
            "isEmergency": false,
            "reason": "Routine consultation",
            "doctor": {
                "id": 2,
                "username": "dr_john_doe"
            },
            "patient": {
                "id": 3,
                "username": "patient_jane_doe"
            }
        }
    ]
    ```

#### Create an Appointment

- **Endpoint**: `POST /consultations`
- **Permissions**: `ROLE_ADMIN`, `ROLE_DOCTOR`, `ROLE_RECEPTIONIST`
- **Request Body**:
    ```json
    {
        "date": "2024-09-01",
        "diagnosis": "Sinusitis",
        "durationMinutes": 30,
        "isEmergency": false,
        "reason": "Routine consultation",
        "doctorId": 2,
        "patientId": 3,
        "symptoms": ["FEVER", "COUGH"]
    }
    ```
- **Response**:
    ```json
    {
        "id": 1,
        "date": "2024-09-01",
        "diagnosis": "Sinusitis",
        "durationMinutes": 30,
        "isEmergency": false,
        "reason": "Routine consultation",
        "doctor": {
            "id": 2,
            "username": "dr_john_doe"
        },
        "patient": {
            "id": 3,
            "username": "patient_jane_doe"
        },
        "symptoms": ["FEVER", "COUGH"]
    }
    ```

#### Update an Appointment

- **Endpoint**: `PUT /consultations/{id}`
- **Permissions**: `ROLE_ADMIN`, `ROLE_DOCTOR`, `ROLE_RECEPTIONIST`
- **Request Body**:
    ```json
    {
        "date": "2024-09-05",
        "diagnosis": "Chronic Sinusitis",
        "durationMinutes": 40,
        "isEmergency": false,
        "reason": "Follow-up",
        "symptoms": ["FEVER", "COUGH"]
    }
    ```
- **Response**:
    ```json
    {
        "id": 1,
        "date": "2024-09-05",
        "diagnosis": "Chronic Sinusitis",
        "durationMinutes": 40,
        "isEmergency": false,
        "reason": "Follow-up",
        "doctor": {
            "id": 2,
            "username": "dr_john_doe"
        },
        "patient": {
            "id": 3,
            "username": "patient_jane_doe"
        },
        "symptoms": ["FEVER", "COUGH"]
    }
    ```

#### Delete an Appointment

- **Endpoint**: `DELETE /consultations/{id}`
- **Permissions**: `ROLE_ADMIN`
- **Response**:
    ```json
    {
        "message": "Appointment successfully deleted."
    }
    ```

### Database Configuration

The application uses PostgreSQL as the primary database. Make sure you have PostgreSQL installed and running locally or on a remote server. Update the `application.properties` file with your database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/auth_clinic
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
