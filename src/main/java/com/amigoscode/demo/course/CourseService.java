package com.amigoscode.demo.course;

import com.amigoscode.demo.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseDataAccessService courseDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public CourseService(CourseDataAccessService courseDataAccessService,
                          EmailValidator emailValidator) {
        this.courseDataAccessService = courseDataAccessService;
        this.emailValidator = emailValidator;
    }

    List<Course> getAllCourses() {
        return courseDataAccessService.selectAllCourses();
    }

    void addNewCourse(Course course) {
        addNewCourse(null, course);
    }

    void addNewCourse(UUID courseId, Course course) {
        UUID newCourseId = Optional.ofNullable(courseId)
                .orElse(UUID.randomUUID());

        courseDataAccessService.insertCourse(newCourseId, course);
    }

    public void updateCourse(UUID courseId, Course course) {
        Optional.ofNullable(course.getdepartment());

        Optional.ofNullable(course.getname())
                .filter(fistName -> !StringUtils.isEmpty(fistName))
                .map(StringUtils::capitalize)
                .ifPresent(firstName -> courseDataAccessService.updateName(courseId, firstName));

        Optional.ofNullable(course.getdescription())
                .filter(lastName -> !StringUtils.isEmpty(lastName))
                .map(StringUtils::capitalize)
                .ifPresent(lastName -> courseDataAccessService.updateDescription(courseId, lastName));
    }

//    void deleteCourse(UUID courseId) {
//        CourseDataAccessService.deleteCourseById(courseId);
//    }
}

