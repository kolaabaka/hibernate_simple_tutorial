package com.fx.repository;

import com.fx.entity.Student;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@NoArgsConstructor
public class StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Student saveStudent(Student student){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean deleteStudent(Long id){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();
            var student = session.find(Student.class, id);
            session.remove(student);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Optional<Student> getById(Long id){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();
            return Optional.of(session.find(Student.class, id));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Student> findAll(){
        try(var session = sessionFactory.openSession()){
            List<Student> allStudents = session.createQuery("SELECT s from Student s", Student.class).list();
            return allStudents;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Student updateStudent(Student student){
        try(var session = sessionFactory.openSession()){
            session.beginTransaction();
            student = session.merge(student);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return student;
    }
}
