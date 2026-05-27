package org.example.student_api.repository;

import org.example.student_api.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStudentRepository implements StudentRepository {

    private final ConcurrentMap<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Student save(Student student) {
        long id = idSequence.getAndIncrement();
        Student saved = new Student(
                id,
                student.getName(),
                student.getEmail(),
                student.getBranch(),
                student.getCgpa()
        );
        students.put(id, saved);
        return saved;
    }

    @Override
    public List<Student> findAll() {
        return students.values().stream()
                .sorted(Comparator.comparing(Student::getId))
                .toList();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public List<Student> findByBranch(String branch) {
        String requestedBranch = branch.trim();
        return students.values().stream()
                .filter(student -> student.getBranch().equalsIgnoreCase(requestedBranch))
                .sorted(Comparator.comparing(Student::getId))
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return students.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        students.remove(id);
    }
}
