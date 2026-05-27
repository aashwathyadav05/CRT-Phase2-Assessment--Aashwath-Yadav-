package org.example.job_application_system.controller;

import org.example.job_application_system.dto.ApplicationResponse;
import org.example.job_application_system.dto.ApplyRequest;
import org.example.job_application_system.entity.Application;
import org.example.job_application_system.service.ApplicationService;
import org.example.job_application_system.service.DuplicateApplicationException;
import org.example.job_application_system.service.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> apply(@RequestBody ApplyRequest request) {
        Application application = applicationService.apply(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(application));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getApplications() {
        List<ApplicationResponse> applications = applicationService.getAllApplications()
                .stream()
                .map(ApplicationResponse::new)
                .toList();
        return ResponseEntity.ok(applications);
    }

    @ExceptionHandler(DuplicateApplicationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateApplication(DuplicateApplicationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleJobNotFound(JobNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}
