package ru.otus.raukhvarger.homework_1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.raukhvarger.homework_1.service.QuestionService;

@ComponentScan
public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);

    private final QuestionService questionService;

    @Autowired
    public Main(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void start() {
        logger.info("Start Application");
        questionService.pleaseSelectLanguage();
        questionService.pleaseInputFIO();
        questionService.pleaseInputAnswer();
        questionService.printResults();
    }

    public static void main(String args[]) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);
        main.start();
    }

}
