package ru.otus.raukhvarger.homework_1.domain.answers;

class IntegerAnswer extends Answer {

    private String value;

    public IntegerAnswer(String answerString, String value) {
        super(answerString);
        this.value = value;
    }

    @Override
    public Boolean checkAnswer(Answer answer) {
        return answer.getValue().equals(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public AnswerType getType() {
        return AnswerType.INTEGER;
    }
}
