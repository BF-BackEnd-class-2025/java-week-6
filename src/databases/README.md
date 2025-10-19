# 🗄️ Introduction to Databases

Databases are at the heart of almost every modern application.
They allow you to **store, organize, and retrieve data efficiently** — instead of keeping information in files or memory,
where it can easily be lost or become inconsistent.

This section introduces **Relational Databases (RDBMS)**, **SQL basics**, and **how data is structured** in a database.

---

## 🧠 1. What Is a Database?

A **database** is an organized collection of data that can be accessed, managed, and updated easily.

### Why do we use databases?

* To **store data permanently**
* To **avoid duplication** and **ensure consistency**
* To **share data** among multiple users or systems
* To **query and analyze** information efficiently

---

## 🧩 2. What Is a Relational Database (RDBMS)?

A **Relational Database Management System (RDBMS)** stores data in **tables** that are related to each other using **keys**.

Popular RDBMS include:

* **PostgreSQL** 🐘
* **MySQL** 🐬
* **SQLite**
* **Oracle**
* **Microsoft SQL Server**

### 📋 RDBMS Characteristics

* Data is stored in **tables**
* Each table has **rows (records)** and **columns (fields)**
* Relationships connect tables logically
* Uses **SQL (Structured Query Language)** for queries

---

## 🧱 3. Database Structure

| Concept            | Description                          | Example                               |
|--------------------|--------------------------------------|---------------------------------------|
| **Table**          | A collection of related data         | `students`, `books`                   |
| **Row (Record)**   | A single entry in a table            | `John, 22, john@email.com`            |
| **Column (Field)** | An attribute of the table            | `name`, `age`, `email`                |
| **Primary Key**    | A unique identifier for each row     | `student_id`                          |
| **Foreign Key**    | A reference linking to another table | `course_id` in `enrollments`          |
| **Relationship**   | Logical link between tables          | A student **enrolls in** many courses |

---

## 🔑 4. Relationships in Databases

| Relationship Type | Description                                            | Example                                    |
|-------------------|--------------------------------------------------------|--------------------------------------------|
| **One-to-One**    | One record in Table A relates to one record in Table B | `User` ↔ `UserProfile`                     |
| **One-to-Many**   | One record in Table A relates to many in Table B       | `Author` → `Books`                         |
| **Many-to-Many**  | Many records in Table A relate to many in Table B      | `Students` ↔ `Courses` (via `Enrollments`) |

---

## 💬 5. Basic SQL Commands

SQL (**Structured Query Language**) is used to communicate with databases.

| Command  | Purpose                    | Example                                  |
|----------|----------------------------|------------------------------------------|
| `CREATE` | Create tables or databases | `CREATE TABLE students (...);`           |
| `INSERT` | Add new records            | `INSERT INTO students VALUES (...);`     |
| `SELECT` | Retrieve data              | `SELECT * FROM students;`                |
| `UPDATE` | Modify existing data       | `UPDATE students SET age=23 WHERE id=1;` |
| `DELETE` | Remove records             | `DELETE FROM students WHERE id=3;`       |

---

## ⚙️ 6. Database Tools

Use these visual tools to connect and manage your databases easily:

| Tool                | Database      | Description                        |
|---------------------|---------------|------------------------------------|
| **pgAdmin**         | PostgreSQL    | Official GUI for PostgreSQL        |
| **MySQL Workbench** | MySQL         | Official GUI for MySQL             |
| **DBeaver**         | All databases | Universal, cross-platform SQL tool |

---

## ✅ Summary

By the end of this section, students should:

* Understand what a **relational database** is
* Know how to design **tables, keys, and relationships**
* Be comfortable writing basic **SQL commands**
* Practice CRUD operations directly in SQL tools
* Prepare to connect databases using **JDBC in Java**

---
