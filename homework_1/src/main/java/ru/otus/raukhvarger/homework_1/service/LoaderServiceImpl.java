package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.Main;
import ru.otus.raukhvarger.homework_1.domain.Question;
import ru.otus.raukhvarger.homework_1.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_1.exeptions.ParserNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class LoaderServiceImpl implements LoaderService {

    private String answersFileName;
    private ParserService parser;

    private List<Question> questions;

    public LoaderServiceImpl(String answersFile, ParserService parser) {
        this.answersFileName = answersFile;
        this.parser = parser;
    }

    @Override
    public List<Question> loadQuestions() {
        if (questions == null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(LoaderServiceImpl.class.getClassLoader().getResourceAsStream(answersFileName)));
            questions = reader.lines().map(this::convertStringToQuestion).collect(Collectors.toList());
        }
        return questions;
    }

    private Question convertStringToQuestion(String s) {
        try {
            return parser.parseQuestion(s);
        } catch (ParserNotFoundException parserNotFound) {
            Main.log("ERROR", "Parser not found: " + s);
        } catch (FailedParsingException e) {
            Main.log("ERROR", "Failed question: " + s);
        }
        return null;
    }

}
