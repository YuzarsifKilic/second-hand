package com.example.secondhand.service;

import com.example.secondhand.dto.request.CreateCustomerRequest;
import com.example.secondhand.dto.model.CustomerDto;
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
    private final DealDeleteService dealService;
    private final OrderDeleteService orderService;

    public CustomerService(CustomerRepository repository,
                           PasswordEncoder passwordEncoder,
                           DealDeleteService dealService,
                           OrderDeleteService orderService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.dealService = dealService;
        this.orderService = orderService;
    }

    @Transactional
    public void createCustomer(CreateCustomerRequest request) {
        final String password = passwordEncoder.encode(request.password());
        Customer customer = Customer.builder()
                .email(request.email())
                .password(password)
                .role(Role.CUSTOMER)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
        repository.save(customer);
    }

    public void deleteCustomer(String id) {
        orderService.deleteOrdersByUserId(id);
        dealService.deleteDealsByUserId(id);
        repository.deleteById(id);
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
