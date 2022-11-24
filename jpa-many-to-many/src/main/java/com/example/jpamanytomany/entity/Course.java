package com.example.jpamanytomany.entity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "COURSE_TBL")
public class Course {

    @Id
    @GeneratedValue
    private  Long id;
    private String title;
    private double fee;

    @ManyToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Student> students;

}
