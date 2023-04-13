package com.example.secondhand.service;

import com.example.secondhand.dto.AdminDto;
import com.example.secondhand.dto.CreateAdminRequest;
import com.example.secondhand.exception.AdminNotFoundException;
import com.example.secondhand.model.Admin;
import com.example.secondhand.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public List<AdminDto> getAll() {
        return repository.findAll()
                .stream()
                .map(AdminDto::convert)
                .collect(Collectors.toList());
    }

    public AdminDto findAdminById(String id) {
        return AdminDto.convert(
                repository.findById(id)
                        .orElseThrow(
                                () -> new AdminNotFoundException("Admin didnt find by id : " + id)));
    }
}
