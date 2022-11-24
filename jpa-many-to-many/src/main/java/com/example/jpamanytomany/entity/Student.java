package com.example.jpamanytomany.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import java.util.Set;

@Entity
@Table(name = "STUDENT_TBL")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String dept;

    @ManyToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinTable(name = "STUDENT_COURSE_TABLE",
    joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name ="course_id", referencedColumnName = "id")
    })
    private Set<Course> course;


}
