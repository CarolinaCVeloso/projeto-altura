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

    public NewCourse createNewCourse(NewCourseDTO newCourseDTO) {
        Optional<NewCourse> existingCourse = courseRepository.findByCode(newCourseDTO.getCode());
        if (existingCourse.isPresent()) {
            throw new IllegalArgumentException("Course code already exists.");
        }
        if (!isValidInstructorEmail(newCourseDTO.getInstructorEmail())) {
            throw new IllegalArgumentException("Invalid instructor email.");
        }

        NewCourse newCourse = new NewCourse();
        newCourse.setName(newCourseDTO.getName());
        newCourse.setCode(newCourseDTO.getCode());
        newCourse.setDescription(newCourseDTO.getDescription());
        newCourse.setInstructorEmail(newCourseDTO.getInstructorEmail());
        return courseRepository.save(newCourse);
    }

    private boolean isValidInstructorEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deactivateCourse(String code) {
        NewCourse course = courseRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));
        course.setStatus(Status.INACTIVE);
        course.setInactivationDate(LocalDate.now());
        courseRepository.save(course);
    }
}
