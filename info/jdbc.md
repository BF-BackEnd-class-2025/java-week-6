# ☕ Java JDBC 

The **Java Database Connectivity (JDBC)** API allows Java applications to **connect to relational databases** like **PostgreSQL** or
**MySQL**, send SQL commands, and process results.

---

## 🧩 1. What is JDBC?

**JDBC (Java Database Connectivity)** is an API that enables Java programs to execute SQL queries, update data, and manage database connections.

### 💡 JDBC Architecture

```
Java Application
      ↓
  JDBC API (java.sql)
      ↓
  JDBC Driver (PostgreSQL/MySQL)
      ↓
  Database
```

---

## ⚙️ 2. Setting Up PostgreSQL JDBC Driver

If using **Maven**, add the dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

If **no Maven**, download the `.jar` file manually from:
👉 [https://jdbc.postgresql.org/download.html](https://jdbc.postgresql.org/download.html)
Then add it to your project’s classpath in IntelliJ or VS Code.

---

## 🧱 3. Create Database & Table in PostgreSQL

Open your `psql` terminal and run:

```sql
CREATE DATABASE school_db;

\c school_db

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100)
);
```

---

## 🔑 4. Configuration File — `config.properties`

To keep your credentials safe and avoid hardcoding them in Java, store them in a `.properties` file.

```properties
db.url=jdbc:postgresql://localhost:5432/school_db
db.user=postgres
db.password=yourpassword
```

---

## 🔗 5. Loading Configuration in Java

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("⚠️ Failed to read configuration file: " + e.getMessage());
            return null;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to database: " + e.getMessage());
            return null;
        }
    }
}
```

🧠 **Explanation:**

* Reads credentials from `config.properties`
* Returns a `Connection` object to reuse in all database operations
* Uses **try-with-resources** to safely close streams

---

## 🧩 6. Performing CRUD Operations

Below are examples of each operation using `PreparedStatement`.

---

### 🧱 **CREATE (Insert Data)**

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateStudent {
    public static void main(String[] args) {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Alice");
            ps.setInt(2, 22);
            ps.setString(3, "alice@example.com");

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows inserted: " + rows);

        } catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }
}
```

✅ Uses **PreparedStatement** for safety and performance.
✅ `executeUpdate()` → returns number of rows affected.

---

### 🔍 **READ (Select Data)**

```java
import java.sql.*;

public class ReadStudents {
    public static void main(String[] args) {
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("🧾 Students List:");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Read failed: " + e.getMessage());
        }
    }
}
```

✅ `ResultSet` → holds data returned from the database.
✅ `rs.next()` → moves to next row.

---

### ✏️ **UPDATE (Modify Data)**

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStudent {
    public static void main(String[] args) {
        String sql = "UPDATE students SET email = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "new_email@example.com");
            ps.setInt(2, 1);

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows updated: " + rows);

        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }
}
```

✅ Always use `WHERE` to target specific records.
✅ Use parameter binding to avoid SQL injection.

---

### 🗑️ **DELETE (Remove Data)**

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudent {
    public static void main(String[] args) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 2); // delete student with id=2
            int rows = ps.executeUpdate();

            System.out.println("✅ Rows deleted: " + rows);

        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}
```

✅ `executeUpdate()` is also used for `DELETE` queries.

---

## 🔄 7. Handling Exceptions Properly

```java
catch (SQLException e) {
    System.out.println("⚠️ SQL Error: " + e.getMessage());
    System.out.println("SQL State: " + e.getSQLState());
    System.out.println("Error Code: " + e.getErrorCode());
}
```

✅ Helps identify database-level errors (wrong table name, constraint violation, etc.)

---

## 🔒 8. Transactions (Optional but Important)

When performing **multiple related operations**, use transactions for **data consistency**.

```java
try (Connection conn = DBConnection.getConnection()) {
    conn.setAutoCommit(false); // start transaction

    PreparedStatement ps1 = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
    ps1.setDouble(1, 100);
    ps1.setInt(2, 1);
    ps1.executeUpdate();

    PreparedStatement ps2 = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
    ps2.setDouble(1, 100);
    ps2.setInt(2, 2);
    ps2.executeUpdate();

    conn.commit(); // all good, save changes
    System.out.println("✅ Transaction committed!");
}
catch (SQLException e) {
    System.out.println("⚠️ Transaction failed, rolling back...");
    try (Connection conn = DBConnection.getConnection()) {
        conn.rollback();
    } catch (SQLException ex) {
        System.out.println("❌ Rollback failed: " + ex.getMessage());
    }
}
```

✅ Transactions ensure **atomicity** — all-or-nothing execution.

---

## 🧠 9. Best Practices

| Practice                                 | Why                                  |
|------------------------------------------|--------------------------------------|
| Use `PreparedStatement`                  | Prevent SQL injection                |
| Use `try-with-resources`                 | Automatically close connections      |
| Store credentials in `config.properties` | Avoid hardcoding sensitive info      |
| Handle `SQLException` properly           | Understand and debug database issues |
| Use transactions for multi-step updates  | Maintain data integrity              |

---

## 💡 10. Common JDBC Classes Summary

| Class               | Description                                   |
|---------------------|-----------------------------------------------|
| `DriverManager`     | Manages JDBC drivers and connections          |
| `Connection`        | Represents an open connection to the database |
| `Statement`         | Executes simple SQL commands                  |
| `PreparedStatement` | Executes parameterized SQL queries            |
| `ResultSet`         | Stores query results                          |
| `SQLException`      | Handles all SQL-related errors                |

---

## 🧩 11. Example Output

```
✅ Database connected successfully!
✅ Rows inserted: 1
🧾 Students List:
1 | Alice | 22 | alice@example.com
✅ Rows updated: 1
✅ Rows deleted: 1
```

---

## 🎯 Learning Outcomes

By the end of this lesson, students will:

* Configure secure database access with `config.properties`
* Establish JDBC connections properly
* Execute **CRUD operations** using `PreparedStatement`
* Handle `SQLException` and ensure data consistency
* Build a strong foundation for **Spring Boot + JPA (Week 8–9)**

---
