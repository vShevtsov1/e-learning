package com.example.e_learning.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userEstimatesDTO {
    private Exam exam;
    private Integer estimate;

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public userEstimatesDTO(Exam exam, Integer estimate) {
        this.exam = exam;
        this.estimate = estimate;
    }
}
