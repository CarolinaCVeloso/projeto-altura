package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.NewCourse;
import br.com.alura.ProjetoAlura.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private NewCourse course;

    @Column(nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    public Registration() {}

    public Registration(User student, NewCourse course) {
        this.student = student;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public User getStudent() {
        return student;
    }

    public NewCourse getCourse() {
        return course;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
