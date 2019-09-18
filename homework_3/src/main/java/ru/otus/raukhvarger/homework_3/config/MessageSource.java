package ru.otus.raukhvarger.homework_3.config;

import java.util.Locale;

public interface MessageSource {

    String get(String name, Locale locale, Object... params);

    String get(String name, Object... params);

    String get(String name);

}
