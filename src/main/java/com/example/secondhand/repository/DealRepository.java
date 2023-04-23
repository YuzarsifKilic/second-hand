package com.example.secondhand.repository;

import com.example.secondhand.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {

    Optional<List<Deal>> findBySenderIdAndProduct(String senderId, Long productId);
}
