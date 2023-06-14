package com.example.e_learning.questions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class questionAnswersDTO {

    private String answer;

    private Boolean correct;

    public questionAnswersDTO(String answer, Boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }
}
