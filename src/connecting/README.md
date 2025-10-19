# ⚙️ Connecting Java to a Database

This section teaches how to connect a **Java application** to a **PostgreSQL** (or MySQL) database using **JDBC**.
Students will learn how to safely open, use, and close database connections — and how to protect against SQL injection.

---

## 🔑 1. Establishing a Connection

To connect Java to a database, we use the **`DriverManager.getConnection()`** method.

### 🧱 Basic Syntax

```java
Connection conn = DriverManager.getConnection(url, user, password);
```

Where:

* `url` → the database connection string
  (e.g., `jdbc:postgresql://localhost:5432/school_db`)
* `user` → the database username
* `password` → the database password

---

## 🧩 2. Steps to Connect

1. Load database configuration (URL, user, password).
2. Call `DriverManager.getConnection()`.
3. Use a **try-with-resources block** to ensure the connection closes automatically.
4. Catch and handle `SQLException`.

---

## 💡 3. Try-with-Resources

Java’s **try-with-resources** automatically closes the connection when done — even if an error occurs.

```java
try (Connection conn = DriverManager.getConnection(url, user, password)) 
{
    // use the connection here
}
```

You don’t need to call `conn.close()` manually.

---

## ⚠️ 4. Handling Exceptions

When something goes wrong (e.g., wrong password or database not running), JDBC throws a **`SQLException`**.

```java
catch (SQLException e) 
{
    System.out.println("Database error: " + e.getMessage());
}
```

You can also log `e.getSQLState()` or `e.getErrorCode()` for debugging.

---

## 🔒 5. Avoiding SQL Injection

Never build SQL queries using string concatenation:

```java
// ❌ Very dangerous
String sql = "SELECT * FROM users WHERE name = '" + username + "'";
```

Instead, always use **`PreparedStatement`**:

```java
PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
ps.setString(1, username);
```

This protects against SQL injection attacks and improves performance.

---

## 🧠 Learning Outcomes

- By the end of this section, students will:
- Establish a database connection using DriverManager
- Handle SQL exceptions cleanly
- Use try-with-resources to close connections safely
- Protect queries with PreparedStatement
- Read credentials from config files instead of hardcoding them

