package org.example.job_application_system.repository;

import org.example.job_application_system.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
