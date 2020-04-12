package com.amigoscode.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public void addNewCourse(@RequestBody @Valid Course course) {
        courseService.addNewCourse(course);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(@PathVariable("courseId") UUID courseId,
                              @RequestBody Course course) {
        courseService.updateCourse(courseId, course);
    }

//    @DeleteMapping("{courseId}")
//    public void deleteCourse(@PathVariable("courseId") UUID courseId) {
//        courseService.deleteCourse(courseId);
//    }

}
