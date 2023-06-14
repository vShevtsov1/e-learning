package com.example.e_learning.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exam {

    private Long idexam;


    private String name;




    public Exam(String name) {
        this.name = name;
    }

    public Long getIdexam() {
        return idexam;
    }

    public void setIdexam(Long idexam) {
        this.idexam = idexam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "idexam=" + idexam +
                ", name='" + name + '\'' +
                '}';
    }
}

