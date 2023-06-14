package com.example.e_learning.questions.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class questionDTO {
    private String questionTask;
    private List<questionAnswersDTO> answersDTOList;

    public questionDTO(String questionTask, List<questionAnswersDTO> answersDTOList) {
        this.questionTask = questionTask;
        this.answersDTOList = answersDTOList;
    }

    public String getQuestionTask() {
        return questionTask;
    }

    public void setQuestionTask(String questionTask) {
        this.questionTask = questionTask;
    }

    public List<questionAnswersDTO> getAnswersDTOList() {
        return answersDTOList;
    }

    public void setAnswersDTOList(List<questionAnswersDTO> answersDTOList) {
        this.answersDTOList = answersDTOList;
    }
}
