package com.example.secondhand.service;

import com.example.secondhand.dto.PcDto;
import com.example.secondhand.exception.PcNotFoundException;
import com.example.secondhand.repository.PcRepository;
import org.springframework.stereotype.Service;

@Service
public class PcService {

    private final PcRepository repository;

    public PcService(PcRepository repository) {
        this.repository = repository;
    }

    public PcDto findPcById(Long id) {
        return PcDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new PcNotFoundException("Pc didnt find by id : " + id)));
    }
}
