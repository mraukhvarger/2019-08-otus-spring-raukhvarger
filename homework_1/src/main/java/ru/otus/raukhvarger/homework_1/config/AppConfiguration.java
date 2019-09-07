package ru.otus.raukhvarger.homework_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public MessageSource customMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("i18n/bundles", "i18n/input", "i18n/error");
        ms.setDefaultEncoding("UTF-8");

        return new MessageSource() {
            @Override
            public String get(String name, Locale locale, Object... params) {
                return ms.getMessage(name, params, name, locale);
            }

            @Override
            public String get(String name, Object... params) {
                return get(name, Locale.getDefault(), params);
            }

            @Override
            public String get(String name) {
                return get(name, new Object[0]);
            }
        };
    }

}
