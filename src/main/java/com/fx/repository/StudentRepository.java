package com.fx.repository;

import com.fx.entity.Profile;
import com.fx.entity.Student;
import com.fx.service.TransactionHelper;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    @Autowired
    private final SessionFactory sessionFactory;
    @Autowired
    private final TransactionHelper transactionHelper;

    public Student saveStudent(Student student){
        return transactionHelper.executeTransaction(session -> {
            session.persist(student);
            return student;
        });
    }

    public Profile saveProfile(Profile profile){
        return transactionHelper.executeTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }

    public void deleteStudent(Long id){
        transactionHelper.executeTransaction(session -> {
            var student = session.find(Student.class, id);
            session.remove(student);
        });
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
