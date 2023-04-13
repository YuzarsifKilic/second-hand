package com.example.secondhand.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Admin extends User {

    private String username;

    public Admin(String email, String password,Role role,  String username) {
        super(email, password, role);
        this.username = username;
    }
}
