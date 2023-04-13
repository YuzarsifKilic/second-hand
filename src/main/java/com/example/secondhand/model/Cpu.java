package com.example.secondhand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cpuBrand;
    private String cpuBrandModel;
    @OneToMany(mappedBy = "cpu")
    private Set<Pc> pc;
}
