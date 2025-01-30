package com.studentmanagementsystem.api.service.impl;

import com.studentmanagementsystem.api.dto.request.CourseRequestDto;
import com.studentmanagementsystem.api.exception.CourseNotFoundException;
import com.studentmanagementsystem.api.model.Course;
import com.studentmanagementsystem.api.model.Student;
import com.studentmanagementsystem.api.repository.CourseRepository;
import com.studentmanagementsystem.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseRequestDto saveCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setId(courseRequestDto.getId());
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCoursePrice(courseRequestDto.getCoursePrice());
        course.setCourseDuration(courseRequestDto.getCourseDuration());
        course.setMaxStudent(courseRequestDto.getMaxStudent());
        return mapToDto(courseRepository.save(course));

    }

    @Override
    public List<CourseRequestDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CourseRequestDto getCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException("Courses not be found"));
        return mapToDto(course);
    }

    @Override
    public CourseRequestDto updateCourse(CourseRequestDto courseRequestDto, int id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException("Courses not be updated"));
        course.setId(courseRequestDto.getId());
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCoursePrice(courseRequestDto.getCoursePrice());
        course.setCourseDuration(courseRequestDto.getCourseDuration());
        course.setMaxStudent(courseRequestDto.getMaxStudent());
        Course updateCourse = courseRepository.save(course);
        return mapToDto(updateCourse);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException("Courses not be deleted"));
        courseRepository.delete(course);
    }


    private CourseRequestDto mapToDto(Course course){
        return CourseRequestDto.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .courseCode(course.getCoursePrice())
                .courseDuration(course.getCourseDuration())
                .maxStudent(course.getMaxStudent())
                .build();
    }
    private Course mapToEntity(CourseRequestDto courseRequestDto){
        return Course.builder()
                .id(courseRequestDto.getId())
                .courseName(courseRequestDto.getCourseName())
                .courseCode(courseRequestDto.getCourseCode())
                .coursePrice(courseRequestDto.getCoursePrice())
                .courseDuration(courseRequestDto.getCourseDuration())
                .maxStudent(courseRequestDto.getMaxStudent())
                .build();
    }

}
