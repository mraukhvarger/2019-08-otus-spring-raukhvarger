package ru.otus.raukhvarger.homework_3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ru.otus.raukhvarger.homework_3.service", "ru.otus.raukhvarger.homework_3.config"})
public class AppConfiguration {

}
