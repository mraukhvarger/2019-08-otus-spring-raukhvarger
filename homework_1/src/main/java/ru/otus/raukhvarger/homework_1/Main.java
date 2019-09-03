package ru.otus.raukhvarger.homework_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.raukhvarger.homework_1.service.QuestionService;

public class Main {

    private QuestionService questionService;

    public Main(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void start() {
        log("INFO", "Start Application");
        questionService.pleaseInputFIO();
        questionService.pleaseInputAnswer();
        questionService.printResults();
    }

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

    public static void log(String level, Object s) {
        System.out.println(level + ": " + s);
    }

}
