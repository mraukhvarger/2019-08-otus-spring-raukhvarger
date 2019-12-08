package ru.otus.raukhvarger.homework_3.domain.answers;

class StringAnswer extends Answer {

    private String value;

    public StringAnswer(String answerString, String value) {
        super(answerString);
        this.value = value;
    }

    @Override
    public Boolean checkAnswer(Answer answer) {
        return ((String) answer.getValue()).toLowerCase().equals(value.toLowerCase());
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public AnswerType getType() {
        return AnswerType.STRING;
    }
}
