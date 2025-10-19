-- Create a new database (you can skip this if already created)
CREATE DATABASE school_db;

-- Switch to the new database
\c school_db; -- For PostgresSQL only

-- Create a 'students' table
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE
);
