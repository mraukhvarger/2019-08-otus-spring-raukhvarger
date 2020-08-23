package ru.otus.raukhvarger.homework_8.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "ru.otus.raukhvarger.homework_8")
@EntityScan("ru.otus.raukhvarger.homework_8.db")
@EnableMongoRepositories(basePackages = {"ru.otus.raukhvarger.homework_8.db"})
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
