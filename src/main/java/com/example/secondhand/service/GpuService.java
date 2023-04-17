package com.example.secondhand.service;

import com.example.secondhand.dto.model.GpuDto;
import com.example.secondhand.exception.GpuNotFoundException;
import com.example.secondhand.model.Gpu;
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

    public Gpu findGpuById(int id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new GpuNotFoundException("Gpu didnt find by id : " + id));
    }
}
