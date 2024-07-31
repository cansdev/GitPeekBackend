# GitPeekBackend

GitPeekBackend is the backend service for the GitPeek application, developed using Spring Boot. It connects to a MySQL database to store and manage bookmark and user data persistently.

## Features

- **User Management**: Handles user authentication and authorization.
- **Bookmark Management**: Allows users to bookmark GitHub repositories.
- **Profile Management**: Manages user profiles and their associated data.

## Technologies Used

- **Spring Boot**: Backend framework for creating the RESTful API.
- **MySQL**: Relational database for persistent data storage.
- **Hibernate**: ORM tool for database operations.

## Model-View-Controller (MVC) Diagram

<img width="1244" alt="image" src="https://github.com/user-attachments/assets/e564c8e5-bc17-4865-b5e9-b821c5bfa8ce">

## Important Notice

**Security Alert:** Earlier commits in this repository may contain sensitive information, including an SQL password. Please be aware that this password has since been changed, and the old database is no longer in use. To ensure your application’s security and proper functionality, please set up your own MySQL instance and configure it as described below.

## Setting Up Your MySQL Database

1. **Install MySQL:**
   If you don't already have MySQL installed, download and install it from the [official MySQL website](https://dev.mysql.com/downloads/).

2. **Create a Database:**
   Open a terminal or command prompt and log into MySQL using the following command:
   
   `mysql -u root -p`
   
   After entering your root password, create a new database for the application:
   
   `CREATE DATABASE gitpeek_backend_db;`

3. **Create a Database User:**
   You may want to create a dedicated user for your application:
   
   `CREATE USER 'gitpeek_user'@'localhost' IDENTIFIED BY 'your_password';`
   
   Grant privileges to the user:
   
   `GRANT ALL PRIVILEGES ON gitpeek_backend_db.* TO 'gitpeek_user'@'localhost';`
   
   Apply the changes:
   
   `FLUSH PRIVILEGES;`

4. **Update Application Configuration:**
   Configure the application to connect to your new MySQL instance. Set the following environment variables or directly update the `application.properties` file:
   
   `spring.datasource.url=jdbc:mysql://localhost:3306/gitpeek_backend_db`
   
   `spring.datasource.username=gitpeek_user`
   
   `spring.datasource.password=your_password`

   Alternatively, you can set the environment variables:
   - `DB_URL`: Database URL (e.g., `jdbc:mysql://localhost:3306/gitpeek_backend_db`)
   - `DB_USERNAME`: Database username (e.g., `gitpeek_user`)
   - `DB_PASSWORD`: Database password (e.g., `your_password`)

5. **Build and Run the Application:**
   Follow these steps to build and run the application:
   
   `git clone https://github.com/cans4/GitPeekBackend.git`
   
   `cd GitPeekBackend`
   
   `mvn clean install`
   
   `mvn spring-boot:run`

   The application will use JPA/Hibernate to automatically create the required database tables based on the entity classes provided in the project.

## Entity Classes Overview

### `Bookmark` Entity
- **Table Name:** `bookmarks`
- **Columns:**
  - `id` (Primary Key, Auto-generated)
  - `repository_id` (Long, Not Null)
  - `user_id` (Long, Not Null)
  - `created_at` (LocalDateTime, Not Null, Automatically Set)
  - `stars` (Integer)
  - `description` (String)
  - `name` (String)

### `User` Entity
- **Table Name:** `users`
- **Columns:**
  - `id` (Primary Key, Auto-generated)
  - `username` (String, Not Null, Unique)
  - `password` (String, Not Null)
  - `created_at` (LocalDateTime, Not Null, Automatically Set)

## License

This project is licensed under the MIT License. See the LICENSE file for details.
