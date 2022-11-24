package com.example.jpamanytomany.repository;
import com.example.jpamanytomany.entity.Course;
import com.example.jpamanytomany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContaining(String name);


}
