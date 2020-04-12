package com.amigoscode.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CourseDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Course> selectAllCourses() {
        String sql = "" +
                "SELECT " +
                " course_id, " +
                " name, " +
                " description, " +
                " department, " +
                " teacher_name " +
                "FROM course";

        return jdbcTemplate.query(sql, mapCourseFomDb());
    }

    int insertCourse(UUID courseId, Course course) {
        String sql = "" +
                "INSERT INTO course (" +
                " course_id, " +
                " name, " +
                " description, " +
                " department, " +
                " teacher_name) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                courseId,
                course.getname(),
                course.getdescription(),
                course.getdepartment(),
                course.getString()
        );
    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM course " +
                " WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{email},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }

//    List<StudentCourse> selectAllStudentCourses(UUID studentId) {
//        String sql = "" +
//                "SELECT " +
//                " student.student_id, " +
//                " course.course_id, " +
//                " course.name, " +
//                " course.description," +
//                " course.department," +
//                " course.teacher_name," +
//                " student_course.start_date, " +
//                " student_course.end_date, " +
//                " student_course.grade " +
//                "FROM student " +
//                "JOIN student_course USING (student_id) " +
//                "JOIN course         USING (course_id) " +
//                "WHERE student.student_id = ?";
//        return jdbcTemplate.query(
//                sql,
//                new Object[]{studentId},
//                mapStudentCourseFromDb()
//        );
//    }
//
//    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
//        return (resultSet, i) ->
//                new StudentCourse(
//                        UUID.fromString(resultSet.getString("student_id")),
//                        UUID.fromString(resultSet.getString("course_id")),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getString("department"),
//                        resultSet.getString("teacher_name"),
//                        resultSet.getDate("start_date").toLocalDate(),
//                        resultSet.getDate("end_date").toLocalDate(),
//                        Optional.ofNullable(resultSet.getString("grade"))
//                                .map(Integer::parseInt)
//                                .orElse(null)
//                );
//    }

    private RowMapper<Course> mapCourseFomDb() {
        return (resultSet, i) -> {
            String courseIdStr = resultSet.getString("course_id");
            UUID courseId = UUID.fromString(courseIdStr);
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String department = resultSet.getString("department");

            String teacher_name = resultSet.getString("teacher_name");
            return new Course(
                    courseId,
                    name,
                    description,
                    department,
                    teacher_name
            );
        };
    }

    int updateEmail(UUID courseId, String teacher_name) {
        String sql = "" +
                "UPDATE course " +
                "SET teacher_name = ? " +
                "WHERE course_id = ?";
        return jdbcTemplate.update(sql, teacher_name, courseId);
    }

    int updateName(UUID courseId, String name) {
        String sql = "" +
                "UPDATE course " +
                "SET name = ? " +
                "WHERE course_id = ?";
        return jdbcTemplate.update(sql, name, courseId);
    }

    int updateDescription(UUID courseId, String description) {
        String sql = "" +
                "UPDATE course " +
                "SET description = ? " +
                "WHERE course_id = ?";
        return jdbcTemplate.update(sql, description, courseId);
    }

    int deleteCourseById(UUID courseId) {
        String sql = "" +
                "DELETE FROM course " +
                "WHERE course_id = ?";
        return jdbcTemplate.update(sql, courseId);
    }
}
