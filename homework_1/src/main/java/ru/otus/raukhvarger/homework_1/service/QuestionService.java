package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.domain.Question;

public interface QuestionService {

    void pleaseInputFIO();

    void pleaseInputAnswer();

    void printResults();

    String getPrintableQuestion(Question question);

    void pleaseSelectLanguage();
}
