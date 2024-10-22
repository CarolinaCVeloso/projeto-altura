package br.com.alura.ProjetoAlura.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<NewCourse, Long> {
    Optional<NewCourse> findByCode(String code);

}