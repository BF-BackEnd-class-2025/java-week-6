import java.io.*;
import java.sql.*;
import java.util.*;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/school_db";
        String user = "postgres";
        String password = "your_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false); // Start transaction manually

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice'");
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob'");

                conn.commit(); // ✅ Commit if all succeed
                System.out.println("✅ Transaction committed successfully!");
            } catch (SQLException e)
            {
                System.out.println("⚠️ Error during transaction: " + e.getMessage());
                try
                {
                    conn.rollback(); // Try to roll back safely
                    System.out.println("↩️ Transaction rolled back.");
                } catch (SQLException rollbackEx)
                {
                    System.out.println("❌ Rollback failed: " + rollbackEx.getMessage());
                }
            }

        } catch (SQLException e)
        {
            System.out.println("❌ Connection error: " + e.getMessage());
        }
    }
}
