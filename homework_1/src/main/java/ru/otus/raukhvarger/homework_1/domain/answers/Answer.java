package ru.otus.raukhvarger.homework_1.domain.answers;

import java.util.List;

public abstract class Answer {

    private String answerString;

    public static Answer getInstance(AnswerType answerType, String answerString, Object value) {
        try {
            switch (answerType) {
                case INTEGER:
                    return new IntegerAnswer(answerString, (String)value);
                case INTEGER_LIST:
                    return new IntegerListAnswer(answerString, (List<Integer>) value);
                case STRING:
                    return new StringAnswer(answerString, (String) value);
            }
        } catch (Throwable e) {
            System.out.println("Invalid answer type: " + e.getLocalizedMessage());
        }
        return null;
    }

    public Answer(String answerString) {
        this.answerString = answerString;
    }

    public abstract Boolean checkAnswer(Answer answer);

    public abstract Object getValue();

    public String getAnswerString() {
        return answerString;
    }

    public abstract AnswerType getType();
}

