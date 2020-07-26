package ru.otus.raukhvarger.homework_5.utils.messages;

public interface MessageProvider {
    String getMessage(String key);

    String getFormattedMessage(String key, String... args);
}
