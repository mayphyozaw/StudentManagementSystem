package com.studentmanagementsystem.api.service;

import com.studentmanagementsystem.api.dto.request.StudentRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentRequestDto createStudent(StudentRequestDto studentRequestDto);
    List<StudentRequestDto> getAllStudents();
    StudentRequestDto getStudentById(int id);
    StudentRequestDto updateStudent(StudentRequestDto studentRequestDto, int id);
    void deleteStudentById(int id);

}
