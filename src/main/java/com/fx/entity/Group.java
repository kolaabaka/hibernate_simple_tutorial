package com.fx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_group")
@NoArgsConstructor
@Getter
@Setter
public class Group {

    public Group(String number, Long graduationYear){
        this.number = number;
        this.graduationYear = graduationYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "grad_year")
    private Long graduationYear;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> studentList;
}
