-- Count how many students are enrolled in each course
SELECT c.title, COUNT(e.student_id) AS total_students
FROM courses c
LEFT JOIN enrollments e ON c.id = e.course_id
GROUP BY c.title;
