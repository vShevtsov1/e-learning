package com.example.e_learning.exam.DTO;


import com.example.e_learning.questionsAnswers.DTO.QuestionAnswers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class correctAnswersDTO {
    private QuestionAnswers userAnswer;
    private QuestionAnswers corectAnswer;

    public correctAnswersDTO(QuestionAnswers userAnswer, QuestionAnswers corectAnswer) {
        this.userAnswer = userAnswer;
        this.corectAnswer = corectAnswer;
    }

    public QuestionAnswers getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(QuestionAnswers userAnswer) {
        this.userAnswer = userAnswer;
    }

    public QuestionAnswers getCorectAnswer() {
        return corectAnswer;
    }

    public void setCorectAnswer(QuestionAnswers corectAnswer) {
        this.corectAnswer = corectAnswer;
    }
}
