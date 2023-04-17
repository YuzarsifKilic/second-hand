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
public class Customer extends User {

    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer(String email, String password, Role role, String firstName, String lastName) {
        super(email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
