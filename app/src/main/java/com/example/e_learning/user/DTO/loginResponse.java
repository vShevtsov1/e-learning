package com.example.e_learning.user.DTO;

import com.example.e_learning.user.help.Roles;
import com.example.e_learning.user.help.status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class loginResponse {
    private status status;
    private String token;
    private Long id;
    private Roles roles;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public com.example.e_learning.user.help.status getStatus() {
        return status;
    }

    public void setStatus(com.example.e_learning.user.help.status status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public loginResponse(com.example.e_learning.user.help.status status, String token, Long id) {
        this.status = status;
        this.token = token;
        this.id = id;
    }
}
