package ru.otus.raukhvarger.homework_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.raukhvarger.homework_3.config.AppConfiguration;
import ru.otus.raukhvarger.homework_3.service.QuestionService;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackageClasses = {AppConfiguration.class})
public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);

    private final QuestionService questionService;

    @Autowired
    public Main(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostConstruct
    public void start() {
        logger.info("Start Application");
        questionService.pleaseSelectLanguage();
        questionService.pleaseInputFIO();
        questionService.pleaseInputAnswer();
        questionService.printResults();
    }

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }

}
