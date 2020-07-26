package ru.otus.raukhvarger.homework_7.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ru.otus.raukhvarger.homework_7")
@EnableJpaRepositories(basePackages = {"ru.otus.raukhvarger.homework_7.jpa.repository"})
@EntityScan("ru.otus.raukhvarger.homework_7.jpa.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
