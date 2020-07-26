package ru.otus.raukhvarger.homework_6.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ru.otus.raukhvarger.homework_6")
@EntityScan("ru.otus.raukhvarger.homework_6")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
