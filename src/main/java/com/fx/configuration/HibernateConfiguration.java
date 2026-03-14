package com.fx.configuration;

import com.fx.configuration.properties.HibernateConfigurationProperties;
import com.fx.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

    @Autowired
    HibernateConfigurationProperties properties;

    @Bean
    @Scope(value = "singleton")
    public SessionFactory sessionFactory(){
        var config = new Configuration();
        config
            .addAnnotatedClass(Student.class)
            .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
            .setProperty("hibernate.connection.url", properties.getUrl())
            .setProperty("hibernate.connection.username", properties.getUserName())
            .setProperty("hibernate.connection.password", properties.getPassword())
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return config.buildSessionFactory();
    }
}
