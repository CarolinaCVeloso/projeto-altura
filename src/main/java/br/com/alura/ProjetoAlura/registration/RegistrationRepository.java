package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.NewCourse;
import br.com.alura.ProjetoAlura.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByStudentAndCourse(User student, NewCourse course);

    boolean existsByStudentAndCourse(User student, NewCourse course);
}
