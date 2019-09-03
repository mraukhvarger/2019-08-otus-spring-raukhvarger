package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.Main;
import ru.otus.raukhvarger.homework_1.domain.Person;
import ru.otus.raukhvarger.homework_1.domain.answers.Answer;
import ru.otus.raukhvarger.homework_1.domain.Question;
import ru.otus.raukhvarger.homework_1.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_1.exeptions.FailedParsingException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionServiceImpl implements QuestionService {

    private final ParserService parserService;
    private final LoaderService loaderService;

    private Scanner scanner = new Scanner(System.in);

    private Person person;
    private List<Question> questions;
    private List<Map<Question, Boolean>> results;

    public QuestionServiceImpl(ParserService parserService, LoaderService loaderService) {
        this.parserService = parserService;
        this.loaderService = loaderService;
    }

    @Override
    public void pleaseInputFIO() {
        System.out.print("Your first name: ");
        String first = scanner.next();
        System.out.print("Your last name: ");
        String last = scanner.next();

        person = new Person(first, last);
    }

    @Override
    public void pleaseInputAnswer() {
        questions = loaderService.loadQuestions();
        results = questions.stream().map(question -> {
            System.out.println(question.getQuestion());
            System.out.println(getPrintableQuestion(question));
            question.getAnswers().forEach(answer -> {
                System.out.print(answer.getAnswerString());
                if (!answer.getType().equals(AnswerType.STRING)) {
                    System.out.print(" - ");
                    System.out.println(answer.getValue());
                }
            });
            Answer answer = null;
            while (true) {
                String answerString = scanner.next();
                try {
                    answer = parserService.parseAnswer(question.getType(), answerString);
                    break;
                } catch (FailedParsingException e) {
                    Main.log("ERROR", "Failed input, please repeat");
                }
            }
            return Collections.singletonMap(question, question.getTrueAnswer().checkAnswer(answer));
        }).collect(Collectors.toList());
    }

    @Override
    public void printResults() {
        System.out.println(String.format("Result for '%s %s'", person.getFirstName(), person.getLastName()));

        results.forEach(r -> {
            System.out.println(String.format("Question: '%s', result: '%s'",
                    r.entrySet().iterator().next().getKey().getQuestion(),
                    r.entrySet().iterator().next().getValue()));
        });
    }

    @Override
    public String getPrintableQuestion(Question question) {
        String printableQuestion = "Please, input answer ";
        switch (question.getType()) {
            case INTEGER:
                return printableQuestion + "(one selection)";
            case INTEGER_LIST:
                return printableQuestion + "(many selections, with ',' separator)";
            case STRING:
                return printableQuestion + "(text)";
        }
        return null;
    }

}
