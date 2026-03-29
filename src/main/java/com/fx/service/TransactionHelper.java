package com.fx.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransactionHelper {

    @Autowired
    private final SessionFactory sessionFactory;

    public void executeTransaction(Consumer<Session> action){
        Transaction transaction = null;
        try(var session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            action.accept(session);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }
    }

    public <T> T executeTransaction(Function<Session, T> action){
        Transaction transaction = null;
        try(var session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            var result = action.apply(session);

            session.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }
    }

}