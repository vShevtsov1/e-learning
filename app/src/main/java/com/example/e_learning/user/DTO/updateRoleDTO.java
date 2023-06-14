package com.example.e_learning.user.DTO;

import com.example.e_learning.user.help.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class updateRoleDTO {
    private Roles role;
    private Long id;

    public updateRoleDTO(Roles role, Long id) {
        this.role = role;
        this.id = id;
    }
}
