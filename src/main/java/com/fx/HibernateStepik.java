package com.fx;

import com.fx.entity.Group;
import com.fx.entity.Student;
import com.fx.repository.GroupRepository;
import com.fx.repository.StudentRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateStepik {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.fx");

        var sessionFactory = context.getBean(SessionFactory.class);

        var studentRepository= context.getBean(StudentRepository.class);
        var groupRepository= context.getBean(GroupRepository.class);

        var gr1 = groupRepository.saveGroup("1", 2024L);
        var gr2 = groupRepository.saveGroup("2", 2013L);
        var gr3 = groupRepository.saveGroup("3", 2017L);

        var st1 = new Student("Vasya", 22, gr1);
        var st2 = new Student("Joba", 21, gr2);

        studentRepository.saveStudent(st1);
        studentRepository.saveStudent(st2);

        groupRepository.findAll();
    }
}
