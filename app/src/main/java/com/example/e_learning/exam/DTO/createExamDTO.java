package com.example.e_learning.exam.DTO;


import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.user.DTO.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class createExamDTO {
    private String name;
    private List<userDTO> users;
    private List<Questions> questions;

    public createExamDTO(String name, List<userDTO> users, List<Questions> questions) {
        this.name = name;
        this.users = users;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "createExamDTO{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", questions=" + questions +
                '}';
    }
}
