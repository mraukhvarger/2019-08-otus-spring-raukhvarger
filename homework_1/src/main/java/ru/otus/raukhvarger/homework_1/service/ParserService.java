package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.domain.Question;
import ru.otus.raukhvarger.homework_1.domain.answers.Answer;
import ru.otus.raukhvarger.homework_1.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_1.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_1.exeptions.ParserNotFoundException;

public interface ParserService {

    Question parseQuestion(String s) throws ParserNotFoundException, FailedParsingException;

    Answer parseAnswer(AnswerType type, String s) throws FailedParsingException;

}
