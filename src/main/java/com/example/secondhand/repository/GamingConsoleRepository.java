package com.example.secondhand.repository;

import com.example.secondhand.model.GamingConsole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamingConsoleRepository extends JpaRepository<GamingConsole, Long> {
}
