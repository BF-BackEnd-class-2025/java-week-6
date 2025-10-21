-- SQL script to create tables for a simple school database
-- including courses, and enrollments.
-- Create foreign key relationships between tables.

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    teacher VARCHAR(100)
);

CREATE TABLE enrollments (
    id SERIAL PRIMARY KEY,
    course_id INT REFERENCES courses(id)
);
