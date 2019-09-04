package ru.otus.raukhvarger.homework_1.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.raukhvarger.homework_1.domain.Question;
import ru.otus.raukhvarger.homework_1.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_1.exeptions.ParserNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class LoaderServiceImpl implements LoaderService {

    static final Logger logger = LogManager.getLogger(LoaderServiceImpl.class);

    private final String answersFileName;
    private final ParserService parser;

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
            logger.error("Parser not found: " + s);
        } catch (FailedParsingException e) {
            logger.error("Failed question: " + s);
        }
        return null;
    }

}
