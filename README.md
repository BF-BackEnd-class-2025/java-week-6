# Week 6: Intro to Databases & JDBC

This week introduces how Java applications interact with **relational databases** using **JDBC (Java Database Connectivity)**.
Students will learn how to connect Java programs to databases such as **PostgreSQL** or **MySQL**, execute **SQL queries**, and perform **CRUD operations**.

---

## Topics Covered

### **1. Introduction to Databases**

* Understanding what a **database** is and why we use it
* Overview of **Relational Databases (RDBMS)**
* Key concepts:

    * Tables, Rows, Columns
    * Primary & Foreign Keys
    * Relationships (One-to-One, One-to-Many, Many-to-Many)
* Basic **SQL commands**:

    * `CREATE`, `SELECT`, `INSERT`, `UPDATE`, `DELETE`
* Overview of database tools: **pgAdmin**, **MySQL Workbench**, or **DBeaver**

---

### **2. JDBC Fundamentals**

* What is **JDBC** and how it enables communication between Java and databases
* JDBC architecture and components:

    * `DriverManager`
    * `Connection`
    * `Statement` / `PreparedStatement`
    * `ResultSet`
* Loading and registering database drivers
* Setting up a **database URL**, **username**, and **password**

---

### **3. Connecting Java to a Database**

* Establishing a connection using `DriverManager.getConnection()`
* Handling exceptions (`SQLException`)
* Best practices for managing connections:

    * Use of **try-with-resources** for automatic cleanup
    * Avoiding SQL injection by using **PreparedStatement**

---

### **4. Performing CRUD Operations**

* Writing Java code to perform:

    * **Create** → `INSERT` statements
    * **Read** → `SELECT` statements
    * **Update** → `UPDATE` statements
    * **Delete** → `DELETE` statements
* Mapping database rows to Java objects (manual ORM concept)
* Handling query results with `ResultSet`

---

### **5. Working with Prepared Statements & Transactions**

* Using `PreparedStatement` to:

    * Prevent SQL injection
    * Handle parameters safely
    * Improve performance with precompiled SQL
* Managing **transactions**:

    * `commit()` and `rollback()` for data consistency
* Understanding **auto-commit mode**

---

## Activities

* Create and connect to a **PostgreSQL/MySQL** database from Java
* Write code to **insert and read data** from a table
* Implement a mini **student or product database** using CRUD operations
* Experiment with **PreparedStatement** and **ResultSet**
* Handle **SQL exceptions** gracefully and log messages for debugging

---

## Learning Outcomes

By the end of Week 6, students will:

* Understand the **basics of relational databases** and SQL syntax
* Connect Java applications to a database using **JDBC**
* Execute SQL queries programmatically with **Statement** and **PreparedStatement**
* Perform complete **CRUD operations** from Java
* Manage **transactions** and handle **SQL exceptions** properly

---

## Recommended Tools

* **Database:** PostgreSQL or MySQL
* **GUI Tools:** pgAdmin / MySQL Workbench / DBeaver
* **Java Libraries:** JDBC driver for the chosen database (e.g. `postgresql-42.x.jar` or `mysql-connector-j.jar`)

---

