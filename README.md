# GitPeekBackend

This is the backend service for the GitPeek app, developed using Spring Boot. It connects to a MySQL database to store bookmark and user data persistently.

## Features

- **User Management**: Handles user authentication and authorization.
- **Bookmark Management**: Allows users to bookmark GitHub repositories.
- **Profile Management**: Manages user profiles and their associated data.

## Technologies Used

- **Spring Boot**: Backend framework.
- **MySQL**: Database for persistent data storage.
- **Hibernate**: ORM for database operations.

## Getting Started

### Prerequisites

- Java 8 or higher
- MySQL
- Maven

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/cans4/GitPeekBackend.git
    cd GitPeekBackend
    ```

2. Update application properties:

    Configure the database connection and other settings using environment variables.

    ```properties
    spring.datasource.url=${DB_URL}
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## Environment Variables

Set the following environment variables to configure the application:

- `DB_URL`: Database URL (e.g., `jdbc:mysql://localhost:3306/gitpeek_backend_db`)
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password

## License

This project is licensed under the MIT License. See the LICENSE file for details.

