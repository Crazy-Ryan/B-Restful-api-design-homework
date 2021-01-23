package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repo.StudentRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(final StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void deleteStudentById(int studentId) {
        if(!studentRepo.deleteStudentById(studentId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student with id" + studentId + "not found");
        }
    }

    public Student findStudentById(int studentId) {
        Optional<Student> foundStudent = studentRepo.findStudentById(studentId);
        if(foundStudent.isPresent()){
            return foundStudent.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student with id" + studentId + "not found");
        }
    }

    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    public List<Student> getStudentsByGender(String genderStr) {
        Optional<Gender> foundGender = Gender.getGenderByStr(genderStr);
        if(foundGender.isPresent()){
            return studentRepo.getAllStudents().stream()
                    .filter(student -> student.getGender().equals(foundGender.get()))
                    .collect(Collectors.toList());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "gender:" + genderStr + " not found");
        }
    }

    public Student updateStudentInfo(Student updatedStudent) {
        System.out.println(updatedStudent);
        Student originalStudent = findStudentById(updatedStudent.getId());
        return studentRepo.updateStudentInfo(updatedStudent);
    }
}
