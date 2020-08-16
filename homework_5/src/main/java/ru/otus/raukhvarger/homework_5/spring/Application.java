package ru.otus.raukhvarger.homework_5.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.otus.raukhvarger.homework_5")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
