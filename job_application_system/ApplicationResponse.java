package org.example.job_application_system.dto;

import org.example.job_application_system.entity.Application;

import java.time.LocalDate;

public class ApplicationResponse {

    private Long id;
    private Long studentId;
    private LocalDate applicationDate;
    private String status;
    private Long jobId;
    private String companyName;
    private String role;

    public ApplicationResponse(Application application) {
        this.id = application.getId();
        this.studentId = application.getStudentId();
        this.applicationDate = application.getApplicationDate();
        this.status = application.getStatus();
        this.jobId = application.getJob().getId();
        this.companyName = application.getJob().getCompanyName();
        this.role = application.getJob().getRole();
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }
}
