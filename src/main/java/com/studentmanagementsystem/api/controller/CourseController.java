package com.studentmanagementsystem.api.controller;

import com.studentmanagementsystem.api.dto.request.CourseRequestDto;
import com.studentmanagementsystem.api.model.Course;
import com.studentmanagementsystem.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseRequestDto> saveCourse(@RequestBody CourseRequestDto courseRequestDto){
        return new ResponseEntity<>(courseService.saveCourse(courseRequestDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseRequestDto>> getAllCourses(CourseRequestDto course){
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/{id}/getCourse")
    public ResponseEntity<CourseRequestDto> getCourseById(@PathVariable int id){
        CourseRequestDto courseRequestDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseRequestDto);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CourseRequestDto> updateCourse(@RequestBody CourseRequestDto courseRequestDto,@PathVariable("id") int couseId){
        return new ResponseEntity<>(courseService.updateCourse(courseRequestDto, couseId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") int courseId){
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("Course deleted", HttpStatus.OK);
    }
}
