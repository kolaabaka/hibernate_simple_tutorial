package com.fx;

import com.fx.entity.Student;
import com.fx.repository.StudentRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateStepik {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.fx");

        var repository= context.getBean(StudentRepository.class);

        Student st1 = new Student();
        st1.setName("Kuka");
        st1.setAge(19);

        repository.saveStudent(st1);
    }
}
