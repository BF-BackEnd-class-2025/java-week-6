-- List all students and their enrolled courses
SELECT s.name AS student, c.title AS course
FROM students s
JOIN enrollments e ON s.id = e.student_id
JOIN courses c ON c.id = e.course_id;
