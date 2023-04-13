package com.example.secondhand.repository;

import com.example.secondhand.model.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<Cpu, Integer> {
}
