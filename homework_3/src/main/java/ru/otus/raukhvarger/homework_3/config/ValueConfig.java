package ru.otus.raukhvarger.homework_3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ValueConfig {

    private String answerFile;

    private String answerFileRu;

    public String getAnswerFile() {
        return answerFile;
    }

    public String getAnswerFileRu() {
        return answerFileRu;
    }

    public void setAnswerFile(String answerFile) {
        this.answerFile = answerFile;
    }

    public void setAnswerFileRu(String answerFileRu) {
        this.answerFileRu = answerFileRu;
    }
}
