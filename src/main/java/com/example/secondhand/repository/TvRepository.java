package com.example.secondhand.repository;

import com.example.secondhand.model.Tv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvRepository extends JpaRepository<Tv, Long> {
}
