package org.example.student_api.service;

import org.example.student_api.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    List<Student> getStudentsByBranch(String branch);

    void deleteStudent(Long id);
}
