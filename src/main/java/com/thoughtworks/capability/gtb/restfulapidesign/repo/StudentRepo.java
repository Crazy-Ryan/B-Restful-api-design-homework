package com.thoughtworks.capability.gtb.restfulapidesign.repo;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class StudentRepo {
    private List<Student> studentsList;

    public StudentRepo() {
        this.studentsList = new ArrayList<>();
        studentsList.add(new Student("st1", Gender.MALE, "first"));
        studentsList.add(new Student("st2", Gender.MALE, "first"));
        studentsList.add(new Student("st3", Gender.MALE, "first"));
        studentsList.add(new Student("st4", Gender.MALE, "first"));
        studentsList.add(new Student("st5", Gender.FEMALE, "first"));
        studentsList.add(new Student("st6", Gender.FEMALE, "first"));
        studentsList.add(new Student("st7", Gender.FEMALE, "first"));
        studentsList.add(new Student("st8", Gender.FEMALE, "first"));
    }

    public Student addStudent(Student student) {
        this.studentsList.add(student);
        return student;
    }

    public boolean deleteStudentById(int studentId) {
        return this.studentsList.removeIf(student -> student.getId() == studentId);
    }

    public Optional<Student> findStudentById(int studentId) {
        return this.studentsList.stream().filter(student -> student.getId() == studentId).findFirst();
    }

    public List<Student> getAllStudents() {
        return  studentsList;
    }

    public Student updateStudentInfo(Student updatedStudent) {
        Student student = findStudentById(updatedStudent.getId()).get();
        student.setName(updatedStudent.getName());
        student.setGender(updatedStudent.getGender());
        student.setNote(updatedStudent.getNote());
        return student;
    }
}
