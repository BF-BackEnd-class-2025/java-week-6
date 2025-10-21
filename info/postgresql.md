# 🐘 PostgreSQL – Essential Commands

PostgreSQL is one of the most powerful and widely used **relational database management systems (RDBMS)**.
This guide covers the essential commands every **Java backend developer** should know to work confidently with databases.

---

## 📘 1. Database Management

### 🧱 Create a Database

```sql
CREATE DATABASE school_db;
```

✅ Creates a new database named `school_db`.

---

### 📋 List All Databases

```sql
\l
```

✅ Lists all databases in PostgreSQL (use inside the `psql` shell).

---

### 🚀 Connect to a Database

```sql
\c school_db
```

✅ Connects your current session to the `school_db` database.

---

### ❌ Delete a Database

```sql
DROP DATABASE school_db;
```

⚠️ **Irreversible** — permanently deletes the database and all its data.

---

## 🧩 2. Table Management

### 🏗️ Create a Table

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE
);
```

✅ Creates a table with an auto-incrementing `id`, unique `email`, and non-null `name`.

---

### 📋 List All Tables

```sql
\dt
```

✅ Displays all tables in the current database.

---

### 🔍 View Table Structure

```sql
\d students
```

✅ Shows columns, data types, and constraints of the `students` table.

---

### 🧱 Add a New Column

```sql
ALTER TABLE students ADD COLUMN phone VARCHAR(20);
```

✅ Adds a `phone` column to the `students` table.

---

### 💣 Drop a Table

```sql
DROP TABLE students;
```

⚠️ Permanently deletes the table and all its data.

---

## 🧾 3. Inserting Data

### ✏️ Insert One Record

```sql
INSERT INTO students (name, age, email)
VALUES ('Alice', 22, 'alice@example.com');
```

✅ Inserts a new student record.

---

### ⚡ Insert Multiple Records

```sql
INSERT INTO students (name, age, email)
VALUES 
('Bob', 20, 'bob@example.com'),
('Clara', 23, 'clara@example.com');
```

✅ Efficiently inserts multiple rows in one query.

---

## 🔍 4. Reading Data (SELECT)

### 📚 Select All Columns

```sql
SELECT * FROM students;
```

✅ Retrieves all records from the `students` table.

---

### 🎯 Select Specific Columns

```sql
SELECT name, age FROM students;
```

✅ Returns only selected columns.

---

### 🔎 Filter with WHERE

```sql
SELECT * FROM students WHERE age > 21;
```

✅ Fetches only records that match a condition.

---

### 🔤 Pattern Matching (LIKE)

```sql
SELECT * FROM students WHERE name LIKE 'A%';
```

✅ Finds all students whose names start with “A”.

---

### ⚙️ Sort Results

```sql
SELECT * FROM students ORDER BY age DESC;
```

✅ Sorts records by `age` in descending order.

---

### 🔢 Limit Results

```sql
SELECT * FROM students LIMIT 5;
```

✅ Displays only the first 5 rows.

---

## 🧮 5. Updating Data

### ✏️ Update a Record

```sql
UPDATE students
SET email = 'alice@newmail.com'
WHERE id = 1;
```

✅ Updates a single record safely using `WHERE`.

---

### ⚡ Update Multiple Records

```sql
UPDATE students
SET age = age + 1;
```

✅ Increments the `age` field for all students.
⚠️ Always use `WHERE` when updating to avoid mass changes.

---

## 🗑️ 6. Deleting Data

### ❌ Delete a Specific Record

```sql
DELETE FROM students WHERE id = 3;
```

✅ Removes one record by ID.

---

### ⚠️ Delete All Records

```sql
DELETE FROM students;
```

✅ Removes all rows but keeps the table structure.

---

### 💣 Truncate Table (Reset IDs)

```sql
TRUNCATE TABLE students RESTART IDENTITY;
```

✅ Deletes all rows and resets auto-increment IDs.

---

## 🔗 7. Keys & Relationships

### 🧩 Primary Key

Ensures each record is **unique** and **identifiable**.

```sql
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);
```

---

### 🔑 Foreign Key

Links two tables together, enforcing referential integrity.

```sql
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    department_id INT REFERENCES departments(id)
);
```

✅ Guarantees that `department_id` in `employees` exists in `departments`.

---

### 🔗 One-to-Many Example

* One department → many employees
* One employee → belongs to one department

---

## 📊 8. Aggregate Functions

### 🧮 COUNT, SUM, AVG, MIN, MAX

```sql
SELECT COUNT(*) FROM students;        -- total number of students
SELECT AVG(age) FROM students;        -- average age
SELECT MAX(age) FROM students;        -- oldest student
```

✅ Aggregate functions summarize data efficiently.

---

### 🧩 GROUP BY Example

```sql
SELECT age, COUNT(*) AS total
FROM students
GROUP BY age;
```

✅ Groups students by age and counts how many share each age.

---

## ⚙️ 9. Joins (Combining Tables)

### 🧱 INNER JOIN

```sql
SELECT employees.name, departments.name AS department
FROM employees
JOIN departments ON employees.department_id = departments.id;
```

✅ Returns records with matching data in both tables.

---

### 🧩 LEFT JOIN

```sql
SELECT employees.name, departments.name
FROM employees
LEFT JOIN departments ON employees.department_id = departments.id;
```

✅ Returns all employees, even those without a department.

---

## 🔐 10. Constraints Overview

| Constraint    | Description              | Example                              |
|---------------|--------------------------|--------------------------------------|
| `PRIMARY KEY` | Unique record identifier | `id SERIAL PRIMARY KEY`              |
| `FOREIGN KEY` | Links between tables     | `REFERENCES departments(id)`         |
| `UNIQUE`      | Prevents duplicates      | `email VARCHAR(100) UNIQUE`          |
| `NOT NULL`    | Field cannot be empty    | `name VARCHAR(50) NOT NULL`          |
| `CHECK`       | Validates data           | `age INT CHECK(age > 0)`             |
| `DEFAULT`     | Assigns default value    | `created_at TIMESTAMP DEFAULT NOW()` |

---

## 🧰 11. Import & Export Data

### 📤 Export Table to CSV

```sql
COPY students TO '/tmp/students.csv' DELIMITER ',' CSV HEADER;
```

✅ Exports table data to a CSV file.

---

### 📥 Import Data from CSV

```sql
COPY students(name, age, email)
FROM '/tmp/students.csv' DELIMITER ',' CSV HEADER;
```

✅ Imports data from a CSV file into the table.

---

## 🧠 12. User & Privilege Management

### 👤 Create a User

```sql
CREATE USER sam WITH PASSWORD 'securepass';
```

---

### 🧑‍💻 Grant Privileges

```sql
GRANT ALL PRIVILEGES ON DATABASE school_db TO sam;
```

---

### 🔒 Revoke Privileges

```sql
REVOKE ALL PRIVILEGES ON DATABASE school_db FROM sam;
```

---

## 🧼 13. Useful psql Commands

| Command   | Description               |
|-----------|---------------------------|
| `\q`      | Exit psql shell           |
| `\dt`     | List all tables           |
| `\du`     | List all users            |
| `\dn`     | List all schemas          |
| `\timing` | Show query execution time |
| `\?`      | Show psql help menu       |

---

## 🚀 14. Common PostgreSQL Data Types

| Type         | Description            | Example               |
|--------------|------------------------|-----------------------|
| `INT`        | Whole number           | 25                    |
| `SERIAL`     | Auto-increment integer | 1, 2, 3...            |
| `VARCHAR(n)` | String with limit      | 'Alice'               |
| `TEXT`       | Long text              | essay content         |
| `BOOLEAN`    | True/False value       | TRUE                  |
| `DATE`       | Calendar date          | '2025-10-05'          |
| `TIMESTAMP`  | Date and time          | '2025-10-05 12:00:00' |
| `NUMERIC`    | Decimal number         | 19.99                 |
| `JSON`       | Structured JSON data   | '{"name": "Alice"}'   |

---

## 🧠 Quick Reference Cheat Sheet

| Task          | Command                                                         |
|---------------|-----------------------------------------------------------------|
| Create DB     | `CREATE DATABASE mydb;`                                         |
| Create Table  | `CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(50));` |
| Insert Data   | `INSERT INTO users (name) VALUES ('Alice');`                    |
| Read Data     | `SELECT * FROM users;`                                          |
| Update Data   | `UPDATE users SET name='Bob' WHERE id=1;`                       |
| Delete Data   | `DELETE FROM users WHERE id=1;`                                 |
| Drop Table    | `DROP TABLE users;`                                             |
| Show Tables   | `\dt`                                                           |
| Connect to DB | `\c mydb`                                                       |

---

