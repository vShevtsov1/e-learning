package com.example.e_learning.questions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Questions {

    private Long idquestions;


    private String questionTask;


    public Questions(String questionTask) {
        this.questionTask = questionTask;
    }

    public Questions(Long idquestions, String questionTask) {
        this.idquestions = idquestions;
        this.questionTask = questionTask;
    }

    public Long getIdquestions() {
        return idquestions;
    }

    public void setIdquestions(Long idquestions) {
        this.idquestions = idquestions;
    }

    public String getQuestionTask() {
        return questionTask;
    }

    public void setQuestionTask(String questionTask) {
        this.questionTask = questionTask;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "idquestions=" + idquestions +
                ", questionTask='" + questionTask + '\'' +
                '}';
    }
}
