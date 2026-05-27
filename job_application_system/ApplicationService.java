package org.example.job_application_system.service;

import org.example.job_application_system.dto.ApplyRequest;
import org.example.job_application_system.entity.Application;
import org.example.job_application_system.entity.Job;
import org.example.job_application_system.repository.ApplicationRepository;
import org.example.job_application_system.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    public Application apply(ApplyRequest request) {
        if (request.getStudentId() == null || request.getJobId() == null) {
            throw new IllegalArgumentException("studentId and jobId are required");
        }

        if (applicationRepository.existsByStudentIdAndJob_Id(request.getStudentId(), request.getJobId())) {
            throw new DuplicateApplicationException("Student has already applied for this job");
        }

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        Application application = new Application();
        application.setStudentId(request.getStudentId());
        application.setJob(job);
        application.setApplicationDate(LocalDate.now());
        application.setStatus("APPLIED");

        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}
