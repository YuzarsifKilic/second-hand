package com.example.secondhand.repository;

import com.example.secondhand.model.Pc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcRepository extends JpaRepository<Pc, Long> {
}
