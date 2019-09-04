package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.domain.answers.Answer;
import ru.otus.raukhvarger.homework_1.domain.Question;

import java.util.List;

public interface QuestionService {

    void pleaseInputFIO();

    void pleaseInputAnswer();

    void printResults();

    String getPrintableQuestion(Question question);
}
