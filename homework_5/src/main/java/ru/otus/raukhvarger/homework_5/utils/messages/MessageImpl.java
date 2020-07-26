package ru.otus.raukhvarger.homework_5.utils.messages;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MessageImpl implements MessageProvider {
    private static final String BUNDLE_NAME = "ru.otus.raukhvarger.homework_5.i18n.hw";
    private final Locale locale;

    public MessageImpl() {
        this.locale = LocaleContextHolder.getLocale();
    }

    @Override
    public String getMessage(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME, locale).getString(key);
    }

    @Override
    public String getFormattedMessage(String key, String... args) {
        return String.format(ResourceBundle.getBundle(BUNDLE_NAME, locale).getString(key), args);
    }
}

