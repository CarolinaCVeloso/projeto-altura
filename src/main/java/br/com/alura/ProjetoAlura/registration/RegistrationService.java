package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.course.NewCourse;
import br.com.alura.ProjetoAlura.course.Status;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Registration registerStudent(NewRegistrationDTO newRegistrationDTO) {
        User student = userRepository.findByEmail(newRegistrationDTO.getStudentEmail())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));

        NewCourse course = courseRepository.findByCode(newRegistrationDTO.getCourseCode())
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        if (course.getStatus() != Status.ACTIVE) {
            throw new IllegalArgumentException("Cannot register for an inactive course.");
        }

        if (registrationRepository.existsByStudentAndCourse(student, course)) {
            throw new IllegalArgumentException("Student is already registered for this course.");
        }
        Registration registration = new Registration(student, course);
        return registrationRepository.save(registration);
    }
}
