package ru.otus.raukhvarger.homework_8.spring;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    private static final String CHANGELOGS_PACKAGE = "ru.otus.raukhvarger.homework_8.changelogs";

    @Bean
    public Mongock mongock(MongoProperties mongoProperties, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, mongoProperties.getDatabase(), CHANGELOGS_PACKAGE)
                .build();
    }
}
