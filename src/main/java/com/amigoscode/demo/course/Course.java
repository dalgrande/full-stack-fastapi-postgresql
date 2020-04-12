package com.amigoscode.demo.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Course {

    private final UUID courseId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotBlank
    private final String department;

    @NotNull
    private final String teacher_name;

    public Course(@JsonProperty("courseId") UUID courseId,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("department") String department,
                   @JsonProperty("teacher_name") String teacher_name) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.department = department;
        this.teacher_name = teacher_name;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getname() {
        return name;
    }

    public String getdescription() {
        return description;
    }

    public String getdepartment() {
        return department;
    }

    public String getString() {
        return teacher_name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department='" + department + '\'' +
                ", teacher_name=" + teacher_name +
                '}';
    }
}
