package br.com.alura.ProjetoAlura.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public ResponseEntity<NewCourseDTO> createNewCourse(@RequestBody NewCourseDTO newCourseDTO) {
        try {
            NewCourseDTO createdCourse = courseService.createNewCourse(newCourseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{code}/inactive")
    public ResponseEntity<Void> deactivateCourse(@PathVariable String code) {
        try {
            courseService.deactivateCourse(code);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
