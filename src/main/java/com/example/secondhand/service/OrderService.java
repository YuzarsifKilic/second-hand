package com.example.secondhand.service;

import com.example.secondhand.dto.model.*;
import com.example.secondhand.dto.request.CreateOrderRequest;
import com.example.secondhand.exception.OrderNotFoundException;
import com.example.secondhand.model.Customer;
import com.example.secondhand.model.Order;
import com.example.secondhand.model.Product;
import com.example.secondhand.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final ProductPhotoFindService productPhotoFindService;
    private final ProductService productService;

    public OrderService(OrderRepository repository,
                        CustomerService customerService,
                        ProductPhotoFindService productPhotoFindService,
                        ProductService productService) {
        this.repository = repository;
        this.customerService = customerService;
        this.productPhotoFindService = productPhotoFindService;
        this.productService = productService;
    }

    @Transactional
    public void saveOrder(CreateOrderRequest request) {
        Customer customer = customerService.findCustomer(request.customerId());
        Product product = productService.findProduct(request.productId());

        Order order = Order.builder()
                .customer(customer)
                .product(product)
                .build();
        repository.save(order);

        productService.sellTheProduct(request.productId());
    }

    /*
    public OrderDto findOrderById(Long id) {
        return OrderDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new OrderNotFoundException("Order didnt find by id : " + id)));
    }
     */

    public List<OrderResponseDto> findOrdersByCustomerId(String customerId) {
        return repository.findAll()
                .stream()
                .filter(o -> o.getCustomer().getId().equals(customerId))
                .map(o -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(o.getProduct().getId());
                    Product product = productService.findProduct(o.getProduct().getId());
                    return new OrderResponseDto(
                            o.getId(),
                            o.getProduct().getId(),
                            o.getProduct().getShortDetails(),
                            o.getProduct().getProductBrand().getBrandName(),
                            o.getProduct().getPrice(),
                            imageUrl);
                })
                .collect(Collectors.toList());
    }


}
