package ru.otus.raukhvarger.homework_3.service;

import ru.otus.raukhvarger.homework_3.domain.Question;

public interface QuestionService {

    void askForFullName();

    void askForAnswers();

    void printResults();

    String getPrintableQuestion(Question question);

    void askForLanguage();
}
