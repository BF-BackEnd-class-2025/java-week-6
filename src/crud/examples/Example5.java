package crud.examples;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Example5
{
    public static void main(String[] args)
    {
        Properties props = new Properties();
        String filePath = "resources" + File.separator + "config.properties";
        try (FileInputStream fis = new FileInputStream(filePath))
        {
            // Load the properties file
            props.load(fis);
        }
        catch (IOException e)
        {
            System.out.println("Could not read config file: " + e.getMessage());
            return;
        }

        // Get values from properties file
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                ));
            }

            System.out.println("🎓 Students:");
            students.forEach(System.out::println);
        }
        catch (SQLException e)
        {
            System.out.println("❌ Failed to load students: " + e.getMessage());
        }
    }
}


class Student
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
    public String toString()
    {
        return id + " | " + name + " | " + age + " | " + email;
    }
}