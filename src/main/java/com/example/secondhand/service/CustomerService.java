package com.example.secondhand.service;

import com.example.secondhand.dto.CreateCustomerRequest;
import com.example.secondhand.dto.CustomerDto;
import com.example.secondhand.exception.CustomerNotFoundException;
import com.example.secondhand.model.Customer;
import com.example.secondhand.model.Role;
import com.example.secondhand.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(request.email(),
                passwordEncoder.encode(request.password()),
                Role.CUSTOMER,
                request.firstName(),
                request.lastName());
        repository.save(customer);
    }

    public List<CustomerDto> getCustomers() {
        return repository.findAll()
                .stream()
                .map(CustomerDto::convert)
                .collect(Collectors.toList());
    }

    public CustomerDto findCustomerById(String id) {
        return CustomerDto.convert(findCustomer(id));
    }

    protected Customer findCustomer(String id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer didnt find by id : " + id));
    }
}
