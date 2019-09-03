package ru.otus.raukhvarger.homework_1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.raukhvarger.homework_1.service.QuestionService;

public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);

    private final QuestionService questionService;

    public Main(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void start() {
        logger.info("Start Application");
        questionService.pleaseInputFIO();
        questionService.pleaseInputAnswer();
        questionService.printResults();
    }

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

}
