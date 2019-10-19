package ru.otus.raukhvarger.homework_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_3.domain.Question;
import ru.otus.raukhvarger.homework_3.domain.answers.Answer;
import ru.otus.raukhvarger.homework_3.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_3.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_3.exeptions.ParserNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ParserServiceImpl implements ParserService {

    private final LocalizationService ms;

    @Autowired
    public ParserServiceImpl(LocalizationService ms) {
        this.ms = ms;
    }

    @Override
    public Question parseQuestion(String s) throws ParserNotFoundException, FailedParsingException {
        List<String> strs = Arrays.asList(s.split(","));
        String questionString = strs.get(0);
        String questionTypeString = strs.get(1);
        String answersString = strs.get(2);
        String trueAnswerString = strs.get(3);

        return getParser(questionTypeString).parse(questionString, answersString, trueAnswerString);
    }

    @Override
    public Answer parseAnswer(AnswerType type, String s) throws FailedParsingException {
        try {
            switch (type) {
                case INTEGER:
                    return Answer.getInstance(type, "", s);
                case INTEGER_LIST:
                    List<Integer> answers = Arrays.stream(s.split(",")).map(Integer::valueOf).collect(Collectors.toList());
                    return Answer.getInstance(type, "", answers);
                case STRING:
                    return Answer.getInstance(type, "", s);
            }
        } catch (Throwable e) {
            throw new FailedParsingException();
        }
        return null;
    }

    private ConcreteParser getParser(String typeQuestion) throws ParserNotFoundException {
        try {
            AnswerType questionType = AnswerType.valueOf(typeQuestion);
            switch (questionType) {
                case INTEGER:
                    return new IntegerParser();
                case INTEGER_LIST:
                    return new IntegerListParser();
                case STRING:
                    return new StringParser();
            }
        } catch (Throwable e) {
            throw new ParserNotFoundException();
        }
        return null;
    }

    private interface ConcreteParser {
        Question parse(String questionString, String answersString, String trueAnswerString) throws FailedParsingException;
    }

    private class IntegerParser implements ConcreteParser {
        /**
         * Example: 1:one;2:two;3:tree,3
         */

        @Override
        public Question parse(String questionString, String answersString, String trueAnswerString) throws FailedParsingException {
            try {
                List<Answer> answers = Arrays.stream(answersString.split(";"))
                        .map(s -> s.split(":"))
//                        .map(strs -> i18nList.apply(strs))
                        .map(strs -> Answer.getInstance(AnswerType.INTEGER, ms.get(strs[1]), strs[0]))
                        .collect(Collectors.toList());

                Answer answer = Answer.getInstance(AnswerType.INTEGER, "", trueAnswerString);
                return new Question(ms.get(questionString), answers, answer);
            } catch (Throwable e) {
                throw new FailedParsingException();
            }
        }
    }

    private class IntegerListParser implements ConcreteParser {
        /**
         * Example: 1:one;2:two;3:tree;4:four,2;4
         */

        @Override
        public Question parse(String questionString, String answersString, String trueAnswerString) throws FailedParsingException {
            try {
                List<Answer> answers = Arrays.stream(answersString.split(";"))
                        .map(s -> s.split(":"))
                        .map(strs -> Answer.getInstance(AnswerType.INTEGER, ms.get(strs[1]), strs[0]))
                        .collect(Collectors.toList());

                Answer answer = Answer.getInstance(AnswerType.INTEGER_LIST,
                        "",
                        Arrays.stream(trueAnswerString.split(";"))
                                .map(Integer::valueOf)
                                .collect(Collectors.toList()));
                return new Question(ms.get(questionString), answers, answer);
            } catch (Throwable e) {
                throw new FailedParsingException();
            }
        }
    }

    private class StringParser implements ConcreteParser {
        /**
         * Example: first;second;third,third
         */

        @Override
        public Question parse(String questionString, String answersString, String trueAnswerString) throws FailedParsingException {
            try {
                List<Answer> answers = Arrays.stream(answersString.split(";"))
                        .map(str -> Answer.getInstance(AnswerType.STRING, str, ms.get(str)))
                        .collect(Collectors.toList());

                Answer answer = Answer.getInstance(AnswerType.STRING, "", ms.get(trueAnswerString));
                return new Question(ms.get(questionString), answers, answer);
            } catch (Throwable e) {
                throw new FailedParsingException();
            }
        }
    }

}
