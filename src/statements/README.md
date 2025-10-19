# 🧠 5. Working with Prepared Statements & Transactions

This section teaches how to safely execute SQL queries and manage **transactions** in Java using **JDBC**.

---

## 🔐 1. PreparedStatement Overview

A `PreparedStatement` is a **precompiled SQL statement** that safely handles parameters and prevents SQL injection.

### ✅ Advantages:
- **Security** → avoids SQL injection by separating code from data  
- **Performance** → precompiled and cached by the database  
- **Readability** → cleaner syntax with `?` placeholders  

---

### 🧱 Example Syntax
```java
PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, age) VALUES (?, ?)");
ps.setString(1, "Alice");
ps.setInt(2, 25);
ps.executeUpdate();
````

---

## ⚙️ 2. Preventing SQL Injection

❌ **Bad practice**

```java
String sql = "SELECT * FROM users WHERE name = '" + name + "'";
```

✅ **Good practice**

```java
PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
ps.setString(1, name);
```

---

## 🔄 3. Transactions in JDBC

A **transaction** is a group of one or more SQL operations that execute as a single unit.
If any operation fails, all others can be **rolled back**.

### 🧱 Key Methods

| Method                 | Description                               |
|------------------------|-------------------------------------------|
| `setAutoCommit(false)` | Turns off automatic commits               |
| `commit()`             | Saves all operations to the database      |
| `rollback()`           | Reverts all operations if an error occurs |

---

## 🔒 4. Example Workflow

```java
conn.setAutoCommit(false);
try 
{
    // multiple SQL statements
    conn.commit(); // commit if all successful
} catch (SQLException e) {
    conn.rollback(); // rollback if any fail
}
```

---

## 🧩 5. Auto-Commit Mode

* By default, every SQL statement commits immediately (`autoCommit = true`).
* In transaction mode, you must call `commit()` manually.
* Always return auto-commit to true after the transaction.

---

## 🧠 Best Practices

✅ Always use **PreparedStatement** instead of `Statement`.
✅ Use **transactions** when performing multiple dependent SQL operations.
✅ Wrap transactions in try-catch-finally with rollback for safety.
✅ Always close resources with **try-with-resources**.

---

## 🎯 Learning Outcomes

By the end of this module, students will:

* Use `PreparedStatement` to execute parameterized queries
* Prevent SQL injection
* Manage transactions with `commit()` and `rollback()`
* Understand auto-commit mode

```

---
