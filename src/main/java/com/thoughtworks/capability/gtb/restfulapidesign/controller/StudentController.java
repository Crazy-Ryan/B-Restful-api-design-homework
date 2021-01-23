package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(value = "gender", required = false) String genderStr) {
        if (genderStr != null) {
            return studentService.getStudentsByGender(genderStr);
        } else {
            return studentService.getAllStudents();
        }
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentService.findStudentById(studentId);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudentById(@PathVariable("studentId") int studentId) {
        studentService.deleteStudentById(studentId);
    }

    @PatchMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudentInfo(student);
    }
}
