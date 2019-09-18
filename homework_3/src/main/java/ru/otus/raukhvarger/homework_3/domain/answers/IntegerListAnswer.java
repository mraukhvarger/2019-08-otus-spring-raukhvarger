package ru.otus.raukhvarger.homework_3.domain.answers;

import java.util.List;

class IntegerListAnswer extends Answer {

    private List<Integer> value;

    public IntegerListAnswer(String answerString, List<Integer> value) {
        super(answerString);
        this.value = value;
    }

    @Override
    public Boolean checkAnswer(Answer answer) {
        return ((List) answer.getValue()).stream().filter(value::contains).count() ==  ((List) answer.getValue()).size();
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getAnswerString() {
        return null;
    }

    @Override
    public AnswerType getType() {
        return AnswerType.INTEGER_LIST;
    }
}
