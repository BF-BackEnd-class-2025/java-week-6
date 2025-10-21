# 💾 PostgreSQL Transactions 

A **transaction** in PostgreSQL is a **sequence of one or more SQL operations** executed as a single logical unit of work.
It ensures **data consistency** — either *all operations succeed (commit)* or *none are applied (rollback)*.

---

## 📘 1. What Is a Transaction?

A transaction must satisfy the **ACID** properties:

| Property            | Description                                                               |
|---------------------|---------------------------------------------------------------------------|
| **A – Atomicity**   | All or nothing — if one part fails, the whole transaction is rolled back. |
| **C – Consistency** | Keeps the database in a valid state.                                      |
| **I – Isolation**   | Each transaction runs independently.                                      |
| **D – Durability**  | Committed data is permanently saved.                                      |

---

## ⚙️ 2. Basic Transaction Commands in PostgreSQL

### 🟢 Start a Transaction

```sql
BEGIN;
```

or

```sql
START TRANSACTION;
```

### 🧱 Execute SQL Commands

```sql
INSERT INTO accounts (name, balance) VALUES ('Alice', 500);
UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';
```

### ✅ Commit the Transaction

```sql
COMMIT;
```

✅ Saves all changes permanently.

---

### ❌ Rollback the Transaction

```sql
ROLLBACK;
```

⚠️ Undoes all operations since the last `BEGIN`.

---

### 🧠 Full Example

```sql
BEGIN;

UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob';

COMMIT;
```

If any step fails (for example, `Bob` doesn’t exist), you can safely run:

```sql
ROLLBACK;
```

---

## 🔁 3. Using SAVEPOINT (Partial Rollback)

You can roll back only part of a transaction using **SAVEPOINT**.

```sql
BEGIN;

UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';
SAVEPOINT sp1;

UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';
ROLLBACK TO sp1; -- Undo only the second update

COMMIT;
```

✅ The first update stays; the second one is undone.

---

## 💻 5. Transactions in Java (JDBC)

In Java, transactions are managed using the **Connection** object.

---

### ✅ Example: Commit on Success

```java
import java.sql.*;

public class TransactionExample {
    public static void main(String[] args) 
    {
        String url = "jdbc:postgresql://localhost:5432/school_db";
        String user = "postgres";
        String password = "your_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) 
        {
            conn.setAutoCommit(false); // Start transaction manually

            try (Statement stmt = conn.createStatement()) 
            {
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice'");
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob'");

                conn.commit(); // ✅ Commit if all succeed
                System.out.println("Transaction committed successfully!");
            } 
            catch (SQLException e) 
            {
                conn.rollback(); // ❌ Roll back if any query fails
                System.out.println("Transaction rolled back due to error: " + e.getMessage());
            }
        } catch (SQLException e) 
        {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
```

🎯 **Explanation:**

* `setAutoCommit(false)` begins the transaction manually.
* All SQL statements execute inside one transaction.
* `commit()` saves the changes.
* `rollback()` undoes everything if an exception occurs.

---

### ⚠️ Example: Rollback on Error

```java
try {
    conn.setAutoCommit(false);

    stmt.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice'");
    stmt.executeUpdate("UPDATE accounts SET balance = balance + 100 WHERE name = 'NonExistentUser'");

    conn.commit(); // Won’t reach here
} catch (SQLException e) 
{
    conn.rollback(); // ✅ Revert all changes
    System.out.println("Error occurred, transaction rolled back.");
}
```

---

## 🧠 6. Best Practices

✅ Always disable `autoCommit` when doing multiple related operations.
✅ Wrap your DB operations in `try/catch` blocks.
✅ Always call `commit()` explicitly when all operations succeed.
✅ Use `rollback()` in the `catch` block to ensure data consistency.
✅ Close your `Connection` in a `finally` or use try-with-resources.

---

## ⚡ Quick Recap

| Action               | SQL Command                     | Java Equivalent                        |
|----------------------|---------------------------------|----------------------------------------|
| Start transaction    | `BEGIN;`                        | `conn.setAutoCommit(false);`           |
| Commit transaction   | `COMMIT;`                       | `conn.commit();`                       |
| Rollback transaction | `ROLLBACK;`                     | `conn.rollback();`                     |
| Partial rollback     | `SAVEPOINT sp; ROLLBACK TO sp;` | Not built-in (use multiple statements) |

---
