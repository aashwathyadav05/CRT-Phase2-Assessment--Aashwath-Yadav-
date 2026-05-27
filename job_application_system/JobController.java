package org.example.job_application_system.controller;

import org.example.job_application_system.dto.JobResponse;
import org.example.job_application_system.entity.Job;
import org.example.job_application_system.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JobResponse(createdJob));
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs() {
        List<JobResponse> jobs = jobService.getAllJobs()
                .stream()
                .map(JobResponse::new)
                .toList();
        return ResponseEntity.ok(jobs);
    }
}
