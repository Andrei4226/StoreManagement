# 🛒 StoreManagement

**StoreManagement** is a backend application built with Java 17, using Spring Boot and Maven. It provides a RESTful API for managing products and suppliers. The application includes authentication, role-based access control, validations, custom exceptions and unit testing.

---

## 🧾 Specifications

- Java-based project using Maven
- Framework: Spring Boot
- Product functionalities:
    - `addProduct`, `findByCode`, `findByCategory`, `addTag`,
    - `removeTag`, `updateListOfTags`, `deleteProductByCode`, `updatePrice`,
    - `updateStock`, `findByPriceRange`, `searchByName`, `findAllProducts`
- Supplier functionalities:
    - `createSupplier`, `findAll`, `findById`, `deleteById`, `updateSupplier`
- Authentication and role-based authorization (roles: ADMIN / USER)
- Logging and error handling
- Unit tests for `ProductService` and `SupplierService`
- Java 17+ features: use of `record`
- `data.sql` for database population and use of in-memory `H2` database
- Project documentation (`README.md`)

---

## 🚀 How to Run the Application

1. Make sure you have installed:
    - **JDK 17+**
    - **Maven**


2. Clone the repository:
   ```bash
   git clone https://github.com/Andrei4226/StoreManagement.git
   cd StoreManagement

3. **Run the project using Maven**:
   ```bash
   mvn spring-boot:run

4. **Acess the main interface**:

🔗 [http://localhost:8080](http://localhost:8080)

5. **Authentication and Role-Based Acccess**:
   Once the application is running, you'll be prompted to log in.

- There are two types of users with different access rights:

  - **Admin User**:
    - Username: `admin`
    - Password: `admin123`

  - **Regular User**:
    - Username: `user`
    - Password: `user123`

Depending on the user type, the accessible endpoints and functionalities will vary.
A **logout** option is also available.

6.  **🗃️ Accessing the H2 In-Memory Database**:

While the application is running, you can inspect the in-memory database at:

🔗 [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)

Use the following configuration:

- **Saved Settings**: `Generic H2 (Embedded)`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Driver Class**: `org.h2.Driver`
- **Username**: `sa`
- **Password**: *(leave empty)*

1. Click **"Test Connection"**
2. Then click **"Connect"**

The database is automatically populated at startup using the `data.sql` script.

Using SQL commands, you can verify/filter the data in the tables.

## 🧪 Running Unit Tests

The project includes unit tests for the following services:

- `ProductService`
- `SupplierService`

To execute the tests, run the following command:

```bash
mvn clean test