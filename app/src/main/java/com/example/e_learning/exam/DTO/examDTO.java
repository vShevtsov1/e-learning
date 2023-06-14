package com.example.e_learning.exam.DTO;


import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.questionsAnswers.DTO.QuestionAnswers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class examDTO {
    private Exam exam;
    private Questions questions;
    private List<QuestionAnswers> questionAnswers;

    public examDTO(Exam exam, Questions questions, List<QuestionAnswers> questionAnswers) {
        this.exam = exam;
        this.questions = questions;
        this.questionAnswers = questionAnswers;
    }

    @Override
    public String toString() {
        return "examDTO{" +
                "exam=" + exam +
                ", questions=" + questions +
                ", questionAnswers=" + questionAnswers +
                '}';
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public List<QuestionAnswers> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswers> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}
