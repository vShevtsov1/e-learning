package com.example.e_learning.user.DTO;

import com.example.e_learning.user.help.status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class registerResponse {
    private status status;

    public com.example.e_learning.user.help.status getStatus() {
        return status;
    }

    public void setStatus(com.example.e_learning.user.help.status status) {
        this.status = status;
    }
}
