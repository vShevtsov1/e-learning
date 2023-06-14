package com.example.e_learning.exam.DTO;


import com.example.e_learning.questionsAnswers.DTO.QuestionAnswers;



import java.util.List;


public class usersExamsDTO {
    private List<QuestionAnswers> questionAnswers;
    private Exam exam;

    public usersExamsDTO(List<QuestionAnswers> questionAnswers, Exam exam) {
        this.questionAnswers = questionAnswers;
        this.exam = exam;
    }
}
