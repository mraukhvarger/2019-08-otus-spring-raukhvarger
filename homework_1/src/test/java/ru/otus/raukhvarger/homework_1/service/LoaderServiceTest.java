package ru.otus.raukhvarger.homework_1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.raukhvarger.homework_1.domain.Question;

import java.util.List;

class LoaderServiceTest extends ParentTesting {

    private final String data = "" +
            "q1,INTEGER,1:a1;2:a2,2\n" +
            "q2,INTEGER_LIST,1:a1;2:a2,1;2\n" +
            "q3,STRING,,a1";

    private final ResourceStream rs = new ResourceStreamStringImpl(data);

    private LoaderService loaderService = new LoaderServiceImpl(rs,
            new ParserServiceImpl(ms));

    @Test
    void loadQuestions() {
        List<Question> questions = loaderService.loadQuestions();
        Assertions.assertEquals(3, questions.size());
    }
}