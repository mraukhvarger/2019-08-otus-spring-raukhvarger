package ru.otus.raukhvarger.homework_3.domain;

import ru.otus.raukhvarger.homework_3.domain.answers.Answer;
import ru.otus.raukhvarger.homework_3.domain.answers.AnswerType;

import java.util.List;

public class Question {
    private String question;
    private List<Answer> answers;
    private Answer trueAnswer;

    public Question() {
    }

    public Question(String question, List<Answer> answers, Answer trueAnswer) {
        this.question = question;
        this.answers = answers;
        this.trueAnswer = trueAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(Answer trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public AnswerType getType() {
        return trueAnswer.getType();
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", trueAnswer=" + trueAnswer +
                '}';
    }
}
