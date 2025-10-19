# 🧠 JDBC Fundamentals

**JDBC (Java Database Connectivity)** is an API that allows Java programs to interact with relational databases
such as **PostgreSQL**, **MySQL**, or **SQLite**.

It provides classes and interfaces to **connect**, **execute SQL queries**, and **retrieve results** directly from Java code.

---

## 🔍 1. What Is JDBC?

**JDBC** acts as a bridge between a **Java application** and a **database**.
It enables your program to:

* Open a **connection** to the database
* **Send SQL commands** (SELECT, INSERT, UPDATE, DELETE)
* **Read query results** (using `ResultSet`)
* Handle **transactions** safely

---

## 🧩 2. JDBC Architecture

```
Java Application
     │
     ▼
 JDBC API  (java.sql package)
     │
     ▼
 JDBC Driver  (specific to your DB, e.g. PostgreSQL)
     │
     ▼
 Database (e.g., PostgreSQL / MySQL)
```

* **JDBC API:** provides interfaces like `Connection`, `Statement`, `PreparedStatement`, and `ResultSet`.
* **JDBC Driver:** a `.jar` file that enables Java to talk to your database.

Example driver:

```text
postgresql-42.x.x.jar
```

---

## ⚙️ 3. Core JDBC Classes and Interfaces

| Interface / Class   | Purpose                                         |
|---------------------|-------------------------------------------------|
| `DriverManager`     | Manages database drivers and connections        |
| `Connection`        | Represents a live connection to the database    |
| `Statement`         | Executes static SQL queries (not parameterized) |
| `PreparedStatement` | Executes parameterized (safe) SQL queries       |
| `ResultSet`         | Stores data returned by a SELECT query          |
| `SQLException`      | Handles database-related errors                 |

---

## 🔑 4. JDBC Workflow

Every JDBC program follows these 5 basic steps:

1. **Load the driver (optional in modern Java)**
2. **Establish a connection**
3. **Create a statement**
4. **Execute SQL query**
5. **Process results**
6. **Close all resources**

---

## 🧰 5. Setting Up PostgreSQL with JDBC

### Step 1 — Install PostgreSQL

* Download from: [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
* Create a new database, e.g. `school_db`.

### Step 2 — Add JDBC Driver to Project

If using IntelliJ or VS Code:

* Add the **PostgreSQL JDBC Driver** (`postgresql-42.x.x.jar`) to your classpath.

If using **Maven**, add this dependency:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.7.3</version>
</dependency>
```

---

## 🧠 Learning Outcomes

By the end of this section, students will:

* Understand **JDBC architecture and classes**
* Connect Java applications to **PostgreSQL**
* Execute **SQL commands** from Java
* Retrieve and display query results
* Handle **database errors** safely

---

