package com.studentmanagementsystem.api.service.impl;


import com.studentmanagementsystem.api.dto.request.StudentRequestDto;
import com.studentmanagementsystem.api.exception.StudentNotFoundException;
import com.studentmanagementsystem.api.model.Course;
import com.studentmanagementsystem.api.model.Student;
import com.studentmanagementsystem.api.repository.CourseRepository;
import com.studentmanagementsystem.api.repository.StudentRepository;
import com.studentmanagementsystem.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentRequestDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setId(studentRequestDto.getId());
        student.setName(studentRequestDto.getName());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setEmail(studentRequestDto.getEmail());
        student.setAddress(studentRequestDto.getAddress());
        Course course = courseRepository.findById(studentRequestDto.getCourseId()).orElseThrow(()->new StudentNotFoundException("Course not found with Id" + studentRequestDto.getCourseId()));
        student.setCourse(course);
        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    @Override
    public List<StudentRequestDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public StudentRequestDto getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student not found"));
        return mapToDto(student);
    }

    @Override
    public StudentRequestDto updateStudent(StudentRequestDto studentRequestDto, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be updated"));
        student.setId(studentRequestDto.getId());
        student.setName(studentRequestDto.getName());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setEmail(studentRequestDto.getEmail());
        student.setAddress(studentRequestDto.getAddress());
        Student updateStudent = studentRepository.save(student);
        return mapToDto(updateStudent);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student could not be deleted"));
        studentRepository.delete(student);
    }


    private StudentRequestDto mapToDto(Student student){
        return StudentRequestDto.builder()
                .id(student.getId())
                .name(student.getName())
                .phoneNumber(student.getPhoneNumber())
                .email(student.getEmail())
                .address(student.getAddress())
                .build();
    }

    private Student mapToEntity(StudentRequestDto studentRequestDto){
        return Student.builder()
                .id(studentRequestDto.getId())
                .name(studentRequestDto.getName())
                .phoneNumber(studentRequestDto.getPhoneNumber())
                .email(studentRequestDto.getEmail())
                .address(studentRequestDto.getAddress())
                .build();
    }
}
