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
    private final DealDeleteService dealService;
    private final OrderDeleteService orderService;

    public SellerService(SellerRepository repository,
                         PasswordEncoder passwordEncoder,
                         DealDeleteService dealService,
                         OrderDeleteService orderService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.dealService = dealService;
        this.orderService = orderService;
    }

    @Transactional
    public void createSeller(CreateSellerRequest request) {
        final String password = passwordEncoder.encode(request.password());
        Seller seller = Seller.builder()
                .email(request.email())
                .password(password)
                .role(Role.SELLER)
                .username(request.username())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .build();

        repository.save(seller);
    }

    public List<SellerDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SellerDto::convert)
                .collect(Collectors.toList());
    }

    public void deleteSeller(String id) {
        orderService.deleteOrdersByUserId(id);
        dealService.deleteDealsByUserId(id);
        repository.deleteById(id);
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
