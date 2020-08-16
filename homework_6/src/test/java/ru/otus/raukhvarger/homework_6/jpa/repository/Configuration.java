package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan("ru.otus.raukhvarger.homework_6")
@EntityScan("ru.otus.raukhvarger.homework_6")
public class Configuration {
}
