package ru.otus.raukhvarger.homework_8.utils.messages;

public interface MessageProvider {
    String getMessage(String key);
    String getFormattedMessage(String key, String... args);
}
