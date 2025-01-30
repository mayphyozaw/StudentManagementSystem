package com.studentmanagementsystem.api.controller;

import com.studentmanagementsystem.api.dto.request.StudentRequestDto;
import com.studentmanagementsystem.api.model.Student;
import com.studentmanagementsystem.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/students/")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentRequestDto> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        return new ResponseEntity<>(studentService.createStudent(studentRequestDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<StudentRequestDto>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }
    @GetMapping("/{id}/")
    public ResponseEntity<StudentRequestDto> getStudentById(@PathVariable int id){
        StudentRequestDto studentRequestDto = studentService.getStudentById(id);
        return ok(studentRequestDto);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<StudentRequestDto> updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable("id") int studentId){
        StudentRequestDto response = studentService.updateStudent(studentRequestDto, studentId );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") int studentId){
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

}
