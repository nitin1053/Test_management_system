# Test Case Management API

## 📌 Project Description
This is a **Spring Boot-based Test Case Management API** that allows users to create, update, delete, and retrieve test cases. The API uses **MongoDB** as the database and provides a Swagger UI for API documentation.

---

## 🚀 Getting Started

### **1️⃣ Prerequisites**
Ensure you have the following installed:
- **Java 17+**
- **Maven 3.6+**
- **MongoDB** (Running on `localhost:27017`)
- **Postman or Swagger UI** for testing

---

### **2️⃣ Clone the Repository**
```sh
 git clone https://github.com/your-repo/test-case-management.git
 cd Test_management_system
```

---

### **3️⃣ Configure MongoDB**
Update the **`application.properties`** file with the MongoDB details:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/testcasemanagement
```

---

### **4️⃣ Build and Run the Application**
```sh
mvn clean install
mvn spring-boot:run
```

---

### **5️⃣ Access Swagger UI (API Documentation)**
Once the application is running, open the Swagger UI:
🔗 **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
🔗 **OpenAPI Docs:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📌 API Endpoints

### **1️⃣ Create a Test Case**
- **Method:** `POST`
- **URL:** `/api/testcases`
- **Request Body:**
```json
{
  "title": "Login Test",
  "description": "Verify login functionality",
  "status": "PENDING",
  "priority": "HIGH"
}
```
- **Response:**
```json
{
  "id": "12345",
  "title": "Login Test",
  "description": "Verify login functionality",
  "status": "PENDING",
  "priority": "HIGH",
  "createdAt": "2025-03-20T12:00:00.000+00:00",
  "updatedAt": "2025-03-20T12:00:00.000+00:00"
}
```

---

### **2️⃣ Get All Test Cases**
- **Method:** `GET`
- **URL:** `/api/testcases`
- **Response:**
```json
[
  {
    "id": "12345",
    "title": "Login Test",
    "status": "PENDING"
  }
]
```

---

### **3️⃣ Get a Test Case by ID**
- **Method:** `GET`
- **URL:** `/api/testcases/{id}`
- **Response:**
```json
{
  "id": "12345",
  "title": "Login Test",
  "status": "PENDING"
}
```

---

### **4️⃣ Update a Test Case**
- **Method:** `PUT`
- **URL:** `/api/testcases/{id}`
- **Request Body:**
```json
{
  "title": "Login Test - Updated",
  "status": "IN_PROGRESS"
}
```

---

### **5️⃣ Delete a Test Case**
- **Method:** `DELETE`
- **URL:** `/api/testcases/{id}`
- **Response:**
```json
"Test case deleted successfully!"
```

---

## 📌 Running Tests
To run unit and integration tests, execute:
```sh
mvn test
```

---

## 📌 Project Structure
```
test-case-management/
│── src/
│   ├── main/java/com/testcasemanagement/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── exception/
│   │   ├── config/  (Swagger)
│   ├── main/resources/
│   │   ├── application.properties
│   ├── test/java/com/testcasemanagement/
│   │   ├── TestCaseServiceTest.java
│   │   ├── TestCaseControllerTest.java
│── pom.xml  # Dependencies
│── README.md  # Project documentation
```

---

## 📌 Technologies Used
- **Java 17**
- **Spring Boot 3.2.2**
- **MongoDB**
- **Swagger/OpenAPI**
- **JUnit 5 & Mockito**

---





## 📌 Contact
For queries, contact: **nitinjha1053@gmail.com**


