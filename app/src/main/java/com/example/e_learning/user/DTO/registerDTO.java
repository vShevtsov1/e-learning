package com.example.e_learning.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class registerDTO {
    private String name;
    private String surname;
    private String email;
    private String password;

    public registerDTO(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
