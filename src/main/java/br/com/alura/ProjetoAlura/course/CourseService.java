package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public NewCourseDTO createNewCourse(NewCourseDTO newCourseDTO) {
        Optional<NewCourseDTO> existingCourse = courseRepository.findByCode(newCourseDTO.getCode());
        if (existingCourse.isPresent()) {
            throw new IllegalArgumentException("Course code already exists.");
        }
        if (!isValidInstructorEmail(newCourseDTO.getInstructorEmail())) {
            throw new IllegalArgumentException("Invalid instructor email.");
        }

        NewCourseDTO course = new NewCourseDTO();
        course.setName(newCourseDTO.getName());
        course.setCode(newCourseDTO.getCode());
        course.setDescription(newCourseDTO.getDescription());
        course.setStatus(Status.ACTIVE);
        course.setInstructorEmail(newCourseDTO.getInstructorEmail());
        course.setInactivationDate(null);

        return courseRepository.save(course);
    }

    private boolean isValidInstructorEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deactivateCourse(String code) {
        NewCourseDTO course = courseRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));
        course.setStatus(Status.INACTIVE);
        course.setInactivationDate(LocalDate.now());
        courseRepository.save(course);
    }
}
