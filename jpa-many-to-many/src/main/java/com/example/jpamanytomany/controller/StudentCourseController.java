package com.example.jpamanytomany.controller;

import com.example.jpamanytomany.entity.Course;
import com.example.jpamanytomany.entity.Student;
import com.example.jpamanytomany.repository.CourseRepository;
import com.example.jpamanytomany.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public StudentCourseController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public Student saveStudentWithcourse(@RequestBody  Student student)
    {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> findAllStudents()
    {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudent(Long studentId)
    {
        return studentRepository.findById(studentId).orElse(null);
    }

    @GetMapping("/find/{name}")
    public List<Student> findStudentsContainingByName(@PathVariable String name)
    {
        return studentRepository.findByNameContaining(name);
    }

    @GetMapping("/search/{price}")
    public List<Course> findCourseLessThanPrice(@PathVariable double price)
    {
        return courseRepository.findByFeeLessThan(price);
    }
}
