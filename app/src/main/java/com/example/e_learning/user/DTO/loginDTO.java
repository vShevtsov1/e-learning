package com.example.e_learning.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class loginDTO {
    private String email;
    private String password;

    public loginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
