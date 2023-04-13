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
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String gpuBrand;
    private String gpuBrandModel;
    @OneToMany(mappedBy = "gpu")
    private Set<Pc> pc;
}
