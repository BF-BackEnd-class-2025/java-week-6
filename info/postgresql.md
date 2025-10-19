# 🐘 PostgreSQL – Essential Commands

PostgreSQL is one of the most powerful and widely used **relational database systems**.
This guide covers the most important commands every Java backend developer must know to interact with databases confidently.

---

## 📘 1. Database Management Commands

### 🧱 Create a Database

```sql
CREATE DATABASE school_db;
```

✅ Creates a new database named `school_db`.

---

### 🔍 List Databases

```sql
\l
```

✅ Lists all databases in PostgreSQL (used in `psql` shell).

---

### 🚀 Connect to a Database

```sql
\c school_db
```

✅ Switches your current session to `school_db`.

---

### ❌ Delete a Database

```sql
DROP DATABASE school_db;
```

⚠️ Irreversible — deletes the entire database and its data.

---

## 🧩 2. Table Management Commands

### 🏗️ Create a Table

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE
);
```

✅ Creates a table with an auto-incrementing `id`, unique email, and non-null `name`.

---

### 📋 Show All Tables

```sql
\dt
```

✅ Lists all tables in the current database.

---

### 🔍 View Table Structure

```sql
\d students
```

✅ Displays the columns, data types, and constraints of the `students` table.

---

### 🧱 Alter a Table (Add a Column)

```sql
ALTER TABLE students ADD COLUMN phone VARCHAR(20);
```

✅ Adds a new column named `phone`.

---

### ❌ Drop a Table

```sql
DROP TABLE students;
```

⚠️ Removes the table and all its data permanently.

---

## 🧾 3. Inserting Data

### 📝 Basic INSERT

```sql
INSERT INTO students (name, age, email)
VALUES ('Alice', 22, 'alice@example.com');
```

✅ Inserts a new student record.

---

### 🧰 Insert Multiple Rows

```sql
INSERT INTO students (name, age, email)
VALUES 
('Bob', 20, 'bob@example.com'),
('Clara', 23, 'clara@example.com');
```

✅ Efficient way to add multiple records at once.

---

## 🔍 4. Reading Data (SELECT)

### 🧠 Select All Rows

```sql
SELECT * FROM students;
```

✅ Retrieves all rows and columns from the table.

---

### 🎯 Select Specific Columns

```sql
SELECT name, age FROM students;
```

✅ Returns only selected fields.

---

### 📌 Filtering with WHERE

```sql
SELECT * FROM students WHERE age > 21;
```

✅ Retrieves only rows that match the condition.

---

### 🔤 Pattern Matching (LIKE)

```sql
SELECT * FROM students WHERE name LIKE 'A%';
```

✅ Finds names starting with “A”.

---

### ⚙️ Sorting Results

```sql
SELECT * FROM students ORDER BY age DESC;
```

✅ Sorts results by age (descending order).

---

### 🔢 Limiting Results

```sql
SELECT * FROM students LIMIT 5;
```

✅ Shows only the first 5 rows.

---

## 🧮 5. Updating Data

### ✏️ Update One Record

```sql
UPDATE students
SET email = 'alice@newmail.com'
WHERE id = 1;
```

✅ Updates Alice’s email only.

---

### ✏️ Update Multiple Records

```sql
UPDATE students
SET age = age + 1;
```

✅ Increments all students’ ages by 1.

---

⚠️ Always use `WHERE` to avoid unintentional mass updates.

---

## 🗑️ 6. Deleting Data

### ❌ Delete Specific Record

```sql
DELETE FROM students WHERE id = 3;
```

✅ Deletes one record by ID.

---

### ⚠️ Delete All Records

```sql
DELETE FROM students;
```

✅ Removes all data (table remains).

---

### 💣 Drop Table Data Safely

```sql
TRUNCATE TABLE students RESTART IDENTITY;
```

✅ Deletes all rows and resets auto-increment IDs.

---

## 🔗 7. Keys and Relationships

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

Links two tables together (enforces relationships).

```sql
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    department_id INT REFERENCES departments(id)
);
```

✅ Ensures every `department_id` in `employees` exists in `departments`.

---

### 🔗 One-to-Many Example

* One department → many employees
* One employee → belongs to one department

---

## 📊 8. Aggregate Functions

### 🧮 COUNT, SUM, AVG, MIN, MAX

```sql
SELECT COUNT(*) FROM students;        -- total students
SELECT AVG(age) FROM students;        -- average age
SELECT MAX(age) FROM students;        -- oldest student
```

✅ Use aggregate functions for summary data.

---

### 🧩 GROUP BY Example

```sql
SELECT age, COUNT(*) AS total
FROM students
GROUP BY age;
```

✅ Groups students by age and counts how many have that age.

---

## ⚙️ 9. Joins (Combining Tables)

### 🧱 INNER JOIN

```sql
SELECT employees.name, departments.name AS department
FROM employees
JOIN departments ON employees.department_id = departments.id;
```

✅ Combines data from two tables where there’s a match.

---

### 🧩 LEFT JOIN

```sql
SELECT employees.name, departments.name
FROM employees
LEFT JOIN departments ON employees.department_id = departments.id;
```

✅ Returns all employees, even those without a department.

---

## 🔐 10. Constraints

| Constraint    | Description              | Example                              |
|---------------|--------------------------|--------------------------------------|
| `PRIMARY KEY` | Unique record ID         | `id SERIAL PRIMARY KEY`              |
| `FOREIGN KEY` | Link between tables      | `REFERENCES departments(id)`         |
| `UNIQUE`      | Prevent duplicate values | `email VARCHAR(100) UNIQUE`          |
| `NOT NULL`    | Field cannot be empty    | `name VARCHAR(50) NOT NULL`          |
| `CHECK`       | Validate data            | `age INT CHECK(age > 0)`             |
| `DEFAULT`     | Assign default value     | `created_at TIMESTAMP DEFAULT NOW()` |

---

## 🧰 11. Data Export & Import

### 📤 Export Data to CSV

```sql
COPY students TO '/tmp/students.csv' DELIMITER ',' CSV HEADER;
```

---

### 📥 Import Data from CSV

```sql
COPY students(name, age, email)
FROM '/tmp/students.csv' DELIMITER ',' CSV HEADER;
```

✅ Great for bulk data transfers.

---

## 🧠 12. User & Permission Management

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

## 🧼 13. Utility Commands

| Command   | Description               |
|-----------|---------------------------|
| `\q`      | Exit psql shell           |
| `\dt`     | List all tables           |
| `\du`     | List all users            |
| `\dn`     | List schemas              |
| `\timing` | Show query execution time |
| `\?`      | Show help in psql         |

---

## 🚀 14. Common PostgreSQL Data Types

| Type         | Description            | Example               |
|--------------|------------------------|-----------------------|
| `INT`        | Whole number           | 25                    |
| `SERIAL`     | Auto-increment integer | 1, 2, 3...            |
| `VARCHAR(n)` | String with limit      | 'Alice'               |
| `TEXT`       | Long text              | essay content         |
| `BOOLEAN`    | True/False             | TRUE                  |
| `DATE`       | Calendar date          | '2025-10-05'          |
| `TIMESTAMP`  | Date and time          | '2025-10-05 12:00:00' |
| `NUMERIC`    | Decimal number         | 19.99                 |
| `JSON`       | Structured data        | '{"name": "Alice"}'   |

---

## 🧠 Quick Recap Cheat Sheet

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
