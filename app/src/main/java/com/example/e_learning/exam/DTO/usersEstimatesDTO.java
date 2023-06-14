package com.example.e_learning.exam.DTO;


import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.user.DTO.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class usersEstimatesDTO {
    private userDTO users;
    private Exam exam;
    private Integer estimate;

    public usersEstimatesDTO(userDTO users, Exam exam, Integer estimate) {
        this.users = users;
        this.exam = exam;
        this.estimate = estimate;
    }

    public userDTO getUsers() {
        return users;
    }

    public void setUsers(userDTO users) {
        this.users = users;
    }

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
}
