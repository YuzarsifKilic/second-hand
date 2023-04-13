package com.example.secondhand.service;

import com.example.secondhand.dto.GpuDto;
import com.example.secondhand.repository.GpuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GpuService {

    private final GpuRepository repository;

    public GpuService(GpuRepository repository) {
        this.repository = repository;
    }

    public List<GpuDto> getAllGpu() {
        return repository.findAll()
                .stream()
                .map(GpuDto::convert)
                .collect(Collectors.toList());
    }
}
