package com.example.secondhand.service;

import com.example.secondhand.dto.model.ColorDto;
import com.example.secondhand.exception.ColorNotFoundException;
import com.example.secondhand.model.Color;
import com.example.secondhand.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {

    private final ColorRepository repository;

    public ColorService(ColorRepository repository) {
        this.repository = repository;
    }

    public List<ColorDto> getAllColors() {
        return repository.findAll()
                .stream()
                .map(ColorDto::convert)
                .collect(Collectors.toList());
    }

    protected Color findColorById(int id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ColorNotFoundException("Color didnt find by id : " + id));
    }
}
