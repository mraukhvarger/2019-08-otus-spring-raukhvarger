package ru.otus.raukhvarger.homework_3.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_3.domain.Person;
import ru.otus.raukhvarger.homework_3.domain.Question;
import ru.otus.raukhvarger.homework_3.domain.answers.Answer;
import ru.otus.raukhvarger.homework_3.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_3.exeptions.FailedParsingException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class);

    private final ParserService parserService;
    private final LoaderService loaderService;
    private final LocalizationService ms;

    private Scanner scanner = new Scanner(System.in);

    private Person person;
    private List<Question> questions;
    private List<Map<Question, Boolean>> results;

    @Autowired
    public QuestionServiceImpl(LocalizationService ms, ParserService parserService, LoaderService loaderService) {
        this.ms = ms;
        this.parserService = parserService;
        this.loaderService = loaderService;
    }

    @Override
    public void askForFullName() {
        print(ms.get("pleaseInput.firstName") + ": ");
        String first = scanner.next();
        print(ms.get("pleaseInput.lastName") + ": ");
        String last = scanner.next();

        person = new Person(first, last);
    }

    @Override
    public void askForAnswers() {
        questions = loaderService.loadQuestions();
        results = questions.stream().map(question -> {
            println(question.getQuestion());
            println(getPrintableQuestion(question));
            question.getAnswers().forEach(answer -> {
                print(answer.getAnswerString());
                if (!answer.getType().equals(AnswerType.STRING)) {
                    print(" - ");
                    println(answer.getValue());
                }
            });
            Answer answer = null;
            while (true) {
                String answerString = scanner.next();
                try {
                    answer = parserService.parseAnswer(question.getType(), answerString);
                    break;
                } catch (FailedParsingException e) {
                    logger.error(ms.get("error.failedInput"));
                }
            }
            return Collections.singletonMap(question, question.getTrueAnswer().checkAnswer(answer));
        }).collect(Collectors.toList());
    }

    @Override
    public void printResults() {
        println(String.format("Result for '%s %s'", person.getFirstName(), person.getLastName()));

        results.forEach(r -> {
            println(String.format("Question: '%s', result: '%s'",
                    r.entrySet().iterator().next().getKey().getQuestion(),
                    r.entrySet().iterator().next().getValue()));
        });
    }

    @Override
    public String getPrintableQuestion(Question question) {
        switch (question.getType()) {
            case INTEGER:
                return ms.get("pleaseInput.answer", ms.get("pleaseInput.oneSelection"));
            case INTEGER_LIST:
                return ms.get("pleaseInput.answer", ms.get("pleaseInput.manySelection"));
            case STRING:
                return ms.get("pleaseInput.answer", ms.get("pleaseInput.text"));
        }
        return null;
    }

    @Override
    public void askForLanguage() {
        println(ms.get("pleaseInput.language"));
        println(ms.get("russianLanguage"));
        println(ms.get("englishLanguage"));
        while (true) {
            try {
                Integer answer = Integer.parseInt(scanner.next());
                switch (answer) {
                    case 1:
                        Locale.setDefault(new Locale("ru"));
                        break;
                    case 2:
                        Locale.setDefault(new Locale("en"));
                        break;
                }
                break;
            } catch (Throwable e) {
                logger.error(ms.get("error.failedInput"));
            }
        }
    }

    void print(Object s) {
        System.out.print(String.valueOf(s));
    }

    void println(Object s) {
        System.out.println(String.valueOf(s));
    }

}
