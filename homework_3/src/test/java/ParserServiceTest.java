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
import ru.otus.raukhvarger.homework_3.config.MessageSource;
import ru.otus.raukhvarger.homework_3.domain.Question;
import ru.otus.raukhvarger.homework_3.domain.answers.Answer;
import ru.otus.raukhvarger.homework_3.domain.answers.AnswerType;
import ru.otus.raukhvarger.homework_3.exeptions.FailedParsingException;
import ru.otus.raukhvarger.homework_3.exeptions.ParserNotFoundException;
import ru.otus.raukhvarger.homework_3.service.ParserService;
import ru.otus.raukhvarger.homework_3.service.ParserServiceImpl;

import java.util.List;

@SpringBootTest(classes = ParserServiceTest.Config.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Сервис анализа вопросов и ответов")
class ParserServiceTest {

    @Configuration
    @Import(ParentTestContext.class)
    static class Config {

        @Bean
        ParserService ps(MessageSource ms) {
            return new ParserServiceImpl(ms);
        }

    }

    @Autowired
    ParserService parserService;

    private final String a1Str = "1";
    private final String a2Str = "2,4";
    private final String a3Str = "text";

    private final String aBadTypeStr = "abracadabra";

    private final String q1Str = "q1,INTEGER,1:a1;2:a2,2";
    private final String q2Str = "q2,INTEGER_LIST,1:a1;2:a2,1;2";
    private final String q3Str = "q3,STRING,,a1";

    private final String qBadTypeStr = "q3,STRUNG,,a1";

    @DisplayName("должен корректно анализировать вопрос")
    @Test
    void parseQuestion() throws FailedParsingException, ParserNotFoundException {
        Assertions.assertThrows(ParserNotFoundException.class, () -> parserService.parseQuestion(qBadTypeStr));

        Question q1 = parserService.parseQuestion(q1Str);
        Assertions.assertEquals("q1", q1.getQuestion());
        Assertions.assertEquals(AnswerType.INTEGER, q1.getType());

        Question q2 = parserService.parseQuestion(q2Str);
        Assertions.assertEquals("q2", q2.getQuestion());
        Assertions.assertEquals(AnswerType.INTEGER_LIST, q2.getType());

        Question q3 = parserService.parseQuestion(q3Str);
        Assertions.assertEquals("q3", q3.getQuestion());
        Assertions.assertEquals(AnswerType.STRING, q3.getType());
    }

    @DisplayName("должен корректно анализировать ответ")
    @Test
    void parseAnswer() throws FailedParsingException {
        Assertions.assertThrows(FailedParsingException.class, () -> parserService.parseAnswer(AnswerType.INTEGER_LIST, aBadTypeStr));

        Answer a1 = parserService.parseAnswer(AnswerType.INTEGER, a1Str);
        Assertions.assertEquals("1", a1.getValue());

        Answer a2 = parserService.parseAnswer(AnswerType.INTEGER_LIST, a2Str);
        Assertions.assertArrayEquals(new Integer[]{2, 4}, ((List) a2.getValue()).toArray());


        Answer a3 = parserService.parseAnswer(AnswerType.STRING, a3Str);
        Assertions.assertEquals("text", a3.getValue());
    }
}
