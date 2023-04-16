package com.example.secondhand.controller;

import com.example.secondhand.dto.request.CreateCustomerRequest;
import com.example.secondhand.dto.model.CustomerDto;
import com.example.secondhand.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public void saveCustomer(@RequestBody CreateCustomerRequest request) {
        service.createCustomer(request);
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(service.findCustomerById(id));
    }


}
