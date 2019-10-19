package ru.otus.raukhvarger.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.raukhvarger.homework_3.domain.Question;

import java.util.List;

@SpringBootTest(classes = LoaderServiceTest.Config.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Сервис загрузки вопрос и ответов ")
class LoaderServiceTest extends ParentTestContext {

    @Configuration
    @Import({ParentTestContext.class, ParserServiceImpl.class, LoaderServiceImpl.class})
    static class Config {

        @Bean
        ResourceStream rs() {
            final String data = "" +
                    "q1,INTEGER,1:a1;2:a2,2\n" +
                    "q2,INTEGER_LIST,1:a1;2:a2,1;2\n" +
                    "q3,STRING,,a1";

            return new ResourceStreamStringImpl(data);
        }

    }

    @Autowired
    LoaderService loaderService;

    @DisplayName(" должен возвращать корректное количество записей")
    @Test
    void loadQuestions() {
        List<Question> questions = loaderService.loadQuestions();
        Assertions.assertEquals(3, questions.size());
    }

}