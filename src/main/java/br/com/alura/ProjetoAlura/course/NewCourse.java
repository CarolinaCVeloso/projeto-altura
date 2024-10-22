package br.com.alura.ProjetoAlura.course;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class NewCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private String instructorEmail;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate inactivationDate;

    public NewCourse() {
    }

    public NewCourse(String name, String code, String description, String instructorEmail) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.instructorEmail = instructorEmail;
        this.status = Status.ACTIVE;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(LocalDate inactivationDate) {
        this.inactivationDate = inactivationDate;
    }
}