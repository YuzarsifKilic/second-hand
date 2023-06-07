package com.example.secondhand.controller;

import com.example.secondhand.dto.model.AdminDto;
import com.example.secondhand.service.AdminService;
import com.example.secondhand.service.CustomerService;
import com.example.secondhand.service.SellerService;
import com.example.secondhand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService service;
    private final UserService userService;
    private final CustomerService customerService;
    private final SellerService sellerService;

    public AdminController(AdminService service,
                           UserService userService,
                           CustomerService customerService,
                           SellerService sellerService) {
        this.service = service;
        this.userService = userService;
        this.customerService = customerService;
        this.sellerService = sellerService;
    }

    @GetMapping("/getAll")
    public List<AdminDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> findAdminById(@PathVariable String id) {
        return ResponseEntity.ok(service.findAdminById(id));
    }

    @DeleteMapping("/{id}")
    public void deleterUser(@PathVariable String id) {
        userService.deleterUser(id);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @DeleteMapping("/seler/{id}")
    public void deleteSeller(@PathVariable String id) {
        sellerService.deleteSeller(id);
    }
}
