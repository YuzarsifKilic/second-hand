package com.example.secondhand.service;

import com.example.secondhand.model.Deal;
import com.example.secondhand.repository.DealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealDeleteService {

    private final DealRepository repository;

    public DealDeleteService(DealRepository repository) {
        this.repository = repository;
    }

    protected void deleteDealsByUserId(String id) {
        final List<Deal> dealList = repository.findAll()
                .stream()
                .filter(d -> d.getSenderId().equals(id) || d.getReceiverId().equals(id))
                .collect(Collectors.toList());

        for (Deal deals: dealList) {
            repository.deleteById(deals.getId());
        }
    }
}
