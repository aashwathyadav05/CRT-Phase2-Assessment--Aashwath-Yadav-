package org.example.student_api.repository;

import org.example.student_api.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(Long id);

    List<Student> findByBranch(String branch);

    boolean existsById(Long id);

    void deleteById(Long id);
}
