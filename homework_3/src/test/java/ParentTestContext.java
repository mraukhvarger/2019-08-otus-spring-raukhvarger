import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.raukhvarger.homework_3.config.MessageSource;

import java.util.Locale;

@Configuration
public class ParentTestContext {

    @Bean
    MessageSource ms() {
        return new MessageSource() {
            @Override
            public String get(String name, Locale locale, Object... params) {
                return name;
            }

            @Override
            public String get(String name, Object... params) {
                return name;
            }

            @Override
            public String get(String name) {
                return name;
            }
        };
    }

}

