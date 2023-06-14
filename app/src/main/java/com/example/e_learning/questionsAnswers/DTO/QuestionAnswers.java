package com.example.e_learning.questionsAnswers.DTO;


import com.example.e_learning.questions.DTO.Questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionAnswers {

    private Long answersID;


    private Questions questions;


    private String answer;


    private Boolean correct;

    public QuestionAnswers(Questions questions, String answer, Boolean correct) {
        this.questions = questions;
        this.answer = answer;
        this.correct = correct;
    }

    public Long getAnswersID() {
        return answersID;
    }

    public void setAnswersID(Long answersID) {
        this.answersID = answersID;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "QuestionAnswers{" +
                "answersID=" + answersID +
                ", questions=" + questions +
                ", answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }
}
