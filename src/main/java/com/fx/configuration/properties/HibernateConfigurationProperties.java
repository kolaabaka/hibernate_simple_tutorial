package com.fx.configuration.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
public class HibernateConfigurationProperties {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;
}
