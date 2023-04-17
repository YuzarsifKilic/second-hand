package com.example.secondhand.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Seller extends User {

    private String username;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    public Seller(String email, String password, Role role, String username, String address, String phoneNumber) {
        super(email, password, role);
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
