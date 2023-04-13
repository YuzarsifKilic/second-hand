package com.example.secondhand.service;

import com.example.secondhand.dto.CpuDto;
import com.example.secondhand.repository.CpuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CpuService {

    private final CpuRepository repository;

    public CpuService(CpuRepository repository) {
        this.repository = repository;
    }

    public List<CpuDto> getAllCpu() {
        return repository.findAll()
                .stream()
                .map(CpuDto::convert)
                .collect(Collectors.toList());
    }
}
