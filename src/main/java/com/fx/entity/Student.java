package com.fx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    public Student(String name, int age, Group group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false )
    private int age;

    @OneToOne //don`t need parametrise because already in Profile.class
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(fetch = FetchType.EAGER)//if got trouble with LazyException need add EAGER in Course
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courseList = new ArrayList<>();
}
