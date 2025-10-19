# 🧱 4. Performing CRUD Operations in Java (JDBC)

CRUD stands for the four basic database operations your Java app will perform:
**Create**, **Read**, **Update**, and **Delete**.

Using **JDBC**, you can run SQL commands directly from Java using `PreparedStatement` and `ResultSet`.

---

## 🔑 1. What Are CRUD Operations?

| Operation  | SQL Command | Description                 |
|------------|-------------|-----------------------------|
| **Create** | `INSERT`    | Add a new record to a table |
| **Read**   | `SELECT`    | Retrieve data from a table  |
| **Update** | `UPDATE`    | Modify existing data        |
| **Delete** | `DELETE`    | Remove data from a table    |

---

## 🧩 2. Database Table Example

Assume we have a `students` table in PostgreSQL:

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100)
);
```

---

## ⚙️ 3. Connecting to Database

You’ll use the same `config.properties` setup introduced earlier:

```properties
db.url=jdbc:postgresql://localhost:5432/school_db
db.user=postgres
db.password=yourpassword
```

---

## 🧱 4. Create (INSERT)

```java
import java.sql.*;

public class CreateStudent 
{
    public static void main(String[] args) 
    {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/school_db", 
                    "postgres", 
                    "yourpassword");
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setString(1, "Alice");
            ps.setInt(2, 22);
            ps.setString(3, "alice@example.com");

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows inserted: " + rows);
        }
        catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }
}
```

🧠 **Explanation:**

* `executeUpdate()` returns how many rows were affected.
* Use `PreparedStatement` for safety and performance.

---

## 🔍 5. Read (SELECT)

```java
import java.sql.*;

public class ReadStudents 
{
    public static void main(String[] args) 
    {
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/school_db", 
                    "postgres", 
                    "yourpassword");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) 
        {

            System.out.println("🧾 Student List:");
            while (rs.next()) 
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");

                System.out.printf("%d | %s | %d | %s%n", id, name, age, email);
            }
        }
        catch (SQLException e) 
        {
            System.out.println("❌ Read failed: " + e.getMessage());
        }
    }
}
```

🧠 **Explanation:**

* `ResultSet` stores query results.
* `rs.next()` moves to the next row.
* `rs.getString("column_name")` retrieves column values.

---

## ✏️ 6. Update (UPDATE)

```java
import java.sql.*;

public class UpdateStudent 
{
    public static void main(String[] args) 
    {
        String sql = "UPDATE students SET email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/school_db", 
                    "postgres", 
                    "yourpassword");
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setString(1, "new_email@example.com");
            ps.setInt(2, 1); // update student with id = 1

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows updated: " + rows);
        }
        catch (SQLException e) 
        {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }
}
```

🧠 **Explanation:**

* Update modifies data based on conditions.
* Always use `WHERE` to avoid updating the entire table.

---

## 🗑️ 7. Delete (DELETE)

```java
import java.sql.*;

public class DeleteStudent 
{
    public static void main(String[] args) 
    {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/school_db", 
                    "postgres", 
                    "yourpassword");
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setInt(1, 2); // delete student with id = 2

            int rows = ps.executeUpdate();
            System.out.println("✅ Rows deleted: " + rows);
        }
        catch (SQLException e) 
        {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}
```

🧠 **Explanation:**

* Deletion also uses `PreparedStatement`.
* Always check affected rows to confirm deletion.

---

## 🧩 8. Mapping Rows to Java Objects

This is a **manual version of ORM** — converting database rows into Java objects.

```java
public class Student 
{
    private int id;
    private String name;
    private int age;
    private String email;

    public Student(int id, String name, int age, String email) 
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " | " + email;
    }
}
```

Then in your `ReadStudents` class:

```java
List<Student> students = new ArrayList<>();
while (rs.next()) 
{
    students.add(new Student(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getInt("age"),
        rs.getString("email")
    ));
}
students.forEach(System.out::println);
```

---

# 🧠 Best Practices

✅ Always use **PreparedStatement** instead of Statement
✅ Close all resources using **try-with-resources**
✅ Check affected rows for `UPDATE`, `DELETE`, `INSERT`
✅ Handle all exceptions with clear error messages
✅ Avoid hardcoding credentials (use config file)

---

# 🎓 Learning Outcomes

By the end of this module, students will:

* Perform **full CRUD** operations using JDBC
* Use **PreparedStatement** safely and effectively
* Understand how to **map database data to Java objects**
* Handle **SQLException** professionally
* Write maintainable, real-world database code

---

