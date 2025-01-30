package com.studentmanagementsystem.api.service;

import com.studentmanagementsystem.api.dto.request.CourseRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    CourseRequestDto saveCourse(CourseRequestDto courseRequestDto);
    List<CourseRequestDto> getAllCourses();
    CourseRequestDto getCourseById(int id);
    CourseRequestDto updateCourse(CourseRequestDto courseRequestDto, int id);
    void deleteCourseById(int id);

}
