package ru.otus.raukhvarger.homework_1.service;

import ru.otus.raukhvarger.homework_1.config.MessageSource;

import java.util.Locale;

class ParentTesting {

    final MessageSource ms = new MessageSource() {
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
