package ru.otus.raukhvarger.homework_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.raukhvarger.homework_3.config.AppConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackageClasses = {AppConfiguration.class})
public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);

    @PostConstruct
    public void start() {
        logger.info("Start Application");
    }

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }

}
