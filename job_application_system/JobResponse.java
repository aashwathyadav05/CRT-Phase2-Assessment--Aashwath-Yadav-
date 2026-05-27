package org.example.job_application_system.dto;

import org.example.job_application_system.entity.Job;

import java.math.BigDecimal;

public class JobResponse {

    private Long id;
    private String companyName;
    private String role;
    private BigDecimal packageAmount;
    private String location;

    public JobResponse(Job job) {
        this.id = job.getId();
        this.companyName = job.getCompanyName();
        this.role = job.getRole();
        this.packageAmount = job.getPackageAmount();
        this.location = job.getLocation();
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public BigDecimal getPackageAmount() {
        return packageAmount;
    }

    public String getLocation() {
        return location;
    }
}
