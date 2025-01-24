# Dyanmic Logging Configuration

This project demonstrates a Spring Boot application with advanced features, including Redis integration and asynchronous logging configuration. The project is structured to showcase best practices for managing logs and data across a distributed environment.

---

## **Features**

1. **Redis Integration**
    - Store and retrieve entities (`Department`, `Student`) in both the database and Redis.
    - Utilize Redis Pub/Sub to dynamically manage logging levels via the `log-level-updates` channel.

2. **Dynamic Log Level Management**
    - Change logging levels dynamically at runtime via REST endpoints.
    - Propagate log level changes across the system using Redis Pub/Sub.

3. **Asynchronous and Synchronous Logging**
    - `INFO` level logs are written asynchronously to console and files.
    - `ERROR` level logs are handled synchronously for reliability.
    - Separate files for general logs and error logs.

---

## **Technology Stack**

- **Java**: 11+
- **Spring Boot**: 2.7+
- **Redis**: Standalone setup for caching and Pub/Sub.
- **PostgreSQL**: Database for persisting entities.
- **Logback**: For advanced logging configurations.

---

## **Project Structure**

### **Packages**
- **`controller`**: REST APIs for managing `Department` and `Student` entities and log levels.
- **`entity`**: JPA entity classes (`Department`, `Student`).
- **`repository`**: JPA repositories for database operations.
- **`service`**: Business logic for database and Redis operations.
- **`springsubscriber`**: Subscriber to manage log level updates via Redis Pub/Sub.
- **`config`**: Configuration classes for Redis and logging.

---

## **Setup Instructions**

### **Prerequisites**
1. Install and configure the following:
    - Java 1.8
    - Maven 3.6+
    - Redis (Standalone mode, default port `6379`)
    - PostgreSQL (Default port `5432`, database: `my_database`, username: `admin`, password: `password`)

### **Configuration Files**

#### **`application.properties`**
```properties
spring.application.name=starterproject

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/my_database
spring.datasource.username=admin
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379

# Logging Configuration
logging.level.root=INFO
logging.level.com.kashyap.starterproject=DEBUG
logging.file.name=logs/app.log
logging.file.max-size=10MB
logging.file.max-history=5
```

#### **`logback-spring.xml`**
Refer to the detailed logging configuration in the main source code to enable asynchronous and synchronous logging.

---

## **Endpoints**

### **Department Management**

- **Save a Department to Database**:
  ```
  POST /api/departments
  Body:
  {
      "id": 1,
      "name": "Engineering"
  }
  ```

- **Retrieve a Department from Database**:
  ```
  GET /api/departments/{id}
  ```

- **Save a Department to Redis**:
  ```
  POST /api/departments/redis
  Body:
  {
      "id": 1,
      "name": "Engineering"
  }
  ```

- **Retrieve a Department from Redis**:
  ```
  GET /api/departments/redis/{id}
  ```

### **Log Level Management**

- **Set Log Level Dynamically**:
  ```
  POST /api/logs/set
  Params:
  - loggerName: Package or class name (e.g., `com.kashyap.starterproject`)
  - level: Log level (`INFO`, `DEBUG`, `ERROR`, etc.)
  ```

- **Get Current Log Level**:
  ```
  GET /api/logs/get
  Params:
  - loggerName: Package or class name (e.g., `com.kashyap.starterproject`)
  ```

- **Reset Log Level to Default**:
  ```
  POST /api/logs/reset
  Params:
  - loggerName: Package or class name (e.g., `com.kashyap.starterproject`)
  ```

### **Student Management**

- **Save a Student**:
  ```
  POST /api/students
  Body:
  {
      "id": 1,
      "name": "John Doe",
      "isActive": true
  }
  ```

- **Retrieve a Student**:
  ```
  GET /api/students/{id}
  ```

---

## **Logging Configuration**

The project uses the following logging setup:

1. **Asynchronous Logging** for `INFO` level:
    - Logs are written to the console and file asynchronously to improve performance.

2. **Synchronous Logging** for `ERROR` level:
    - Logs are written synchronously to ensure reliability and immediate feedback during critical issues.

3. **Logback Configuration**:
    - General logs are stored in `logs/app.log`.
    - Error logs are stored in `logs/app-error.log`.

---

## **How to Run the Project**

1. Clone the repository:
   ```
   git clone <repository_url>
   cd starterproject
   ```

2. Build the project using Maven:
   ```
   mvn clean install
   ```

3. Start the application:
   ```
   mvn spring-boot:run
   ```

4. Test the application using the provided endpoints.

---

## **Future Enhancements**

- Add support for Redis Cluster.
- Implement authentication and authorization for REST APIs.
- Extend logging configuration to include custom log filters.

---

## **Contributors**

- Vaibhav Kashyap

---

## **License**

This project is licensed under the MIT License.
