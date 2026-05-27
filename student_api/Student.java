package org.example.student_api.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Student {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "branch is required")
    private String branch;

    @NotNull(message = "cgpa is required")
    @DecimalMin(value = "0.0", message = "cgpa must be at least 0.0")
    @DecimalMax(value = "10.0", message = "cgpa must be at most 10.0")
    private Double cgpa;

    public Student() {
    }

    public Student(Long id, String name, String email, String branch, Double cgpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.cgpa = cgpa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
}
