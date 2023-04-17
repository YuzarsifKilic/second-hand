package com.example.secondhand.service;

import com.example.secondhand.dto.request.CreateSellerRequest;
import com.example.secondhand.dto.model.SellerDto;
import com.example.secondhand.exception.SellerNotFoundException;
import com.example.secondhand.model.Role;
import com.example.secondhand.model.Seller;
import com.example.secondhand.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public SellerService(SellerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createSeller(CreateSellerRequest request) {
        Seller seller = new Seller(request.email(),
                passwordEncoder.encode(request.password()),
                Role.SELLER,
                request.username(),
                request.address(),
                request.phoneNumber());

        repository.save(seller);
    }

    public List<SellerDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SellerDto::convert)
                .collect(Collectors.toList());
    }

    public SellerDto findSellerById(String id) {
        return SellerDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new SellerNotFoundException("Seller didnt find by id : " + id)));
    }

    public Seller findSeller(String id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new SellerNotFoundException("Seller didnt find by id : " + id));
    }
}
