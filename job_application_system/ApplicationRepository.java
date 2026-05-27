package org.example.job_application_system.repository;

import org.example.job_application_system.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndJob_Id(Long studentId, Long jobId);
}
