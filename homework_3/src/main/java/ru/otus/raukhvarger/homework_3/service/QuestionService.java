package ru.otus.raukhvarger.homework_3.service;

import ru.otus.raukhvarger.homework_3.domain.Question;

public interface QuestionService {

    void pleaseInputFIO();

    void pleaseInputAnswer();

    void printResults();

    String getPrintableQuestion(Question question);

    void pleaseSelectLanguage();
}
