package ru.otus.raukhvarger.homework_8.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoProperties {
    private int port;
    private String database;
    private String uri;
}

