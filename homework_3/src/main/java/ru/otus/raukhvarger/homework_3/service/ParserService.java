package ru.otus.raukhvarger.homework_3.service;

import ru.otus.raukhvarger.homework_3.domain.Question;
import ru.otus.raukhvarger.homework_3.domain.answers.Answer;
import ru.otus.raukhvarger.homework_3.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_3.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_3.exeptions.ParserNotFoundException;

public interface ParserService {

    Question parseQuestion(String s) throws ParserNotFoundException, FailedParsingException;

    Answer parseAnswer(AnswerType type, String s) throws FailedParsingException;

}
