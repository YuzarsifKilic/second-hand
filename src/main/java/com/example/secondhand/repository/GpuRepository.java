package com.example.secondhand.repository;

import com.example.secondhand.model.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<Gpu, Integer> {
}
