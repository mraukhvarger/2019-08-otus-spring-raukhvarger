package ru.otus.raukhvarger.homework_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource ms;

    public LocalizationServiceImpl(MessageSource ms) {
        this.ms = ms;
    }

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
}
