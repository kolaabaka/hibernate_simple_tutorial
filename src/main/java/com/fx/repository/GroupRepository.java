package com.fx.repository;

import com.fx.entity.Group;
import com.fx.service.TransactionHelper;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    @Autowired
    private final SessionFactory sessionFactory;
    @Autowired
    private final TransactionHelper transactionHelper;

    public Group saveGroup(String number, Long gradYear){
        return transactionHelper.executeTransaction(session -> {
            var group = new Group(number, gradYear);
            session.persist(group);
            return group;
        });
    }

    public List<Group> findAll(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("""
                SELECT g FROM Group g
                left join fetch g.studentList s
                left join fetch s.profile
                """, Group.class).list();
        }
    }
}
