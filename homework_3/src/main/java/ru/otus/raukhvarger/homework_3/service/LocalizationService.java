package ru.otus.raukhvarger.homework_3.service;

import java.util.Locale;

public interface LocalizationService {

    String get(String name, Locale locale, Object... params);

    String get(String name, Object... params);

    String get(String name);

}
