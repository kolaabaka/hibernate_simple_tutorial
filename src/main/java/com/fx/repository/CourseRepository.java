package com.fx.repository;

import com.fx.entity.Course;
import com.fx.entity.Student;
import com.fx.service.TransactionHelper;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    @Autowired
    private final SessionFactory sessionFactory;
    @Autowired
    private final TransactionHelper transactionHelper;

    public Course saveCourse(Course course){
        return transactionHelper.executeTransaction(session -> {
            session.persist(course);
            return course;
        });
    }

    public void enrollStudentToCourse(Long courseId, Long studentId){
        transactionHelper.executeTransaction(session -> {
//            var student = session.find(Student.class, studentId);
//            var course = session.find(Course.class, courseId);
//
//            student.getCourseList().add(course);
            String query = """
                INSERT INTO student_courses(course_id, student_id) VALUES(:course_id, :student_id);
                """;
            session.createNativeQuery(query, Void.class)
                .setParameter("course_id", courseId)
                .setParameter("student_id", studentId)
                .executeUpdate();
        });
    }
}
