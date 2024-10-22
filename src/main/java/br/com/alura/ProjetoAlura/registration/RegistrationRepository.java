package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.NewCourse;
import br.com.alura.ProjetoAlura.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByStudentAndCourse(User student, NewCourse course);

    boolean existsByStudentAndCourse(User student, NewCourse course);

    @Query(value = "SELECT c.name AS courseName, c.code AS courseCode, u.name AS instructorName, " +
            "u.email AS instructorEmail, COUNT(r) AS totalRegistrations " +
            "FROM Registration r " +
            "JOIN r.course c " +
            "JOIN c.instructor u " +
            "GROUP BY c.id, u.id " +
            "ORDER BY totalRegistrations DESC", nativeQuery = true)
    List<RegistrationReportItem> findCoursesWithMostRegistrations();
}
