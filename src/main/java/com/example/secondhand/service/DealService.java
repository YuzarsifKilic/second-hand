package com.example.secondhand.service;

import com.example.secondhand.dto.model.*;
import com.example.secondhand.dto.request.GetDealRequest;
import com.example.secondhand.dto.request.SendMessageRequest;
import com.example.secondhand.model.Deal;
import com.example.secondhand.model.Product;
import com.example.secondhand.model.Seller;
import com.example.secondhand.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DealService {

    private final DealRepository repository;
    private final ProductService productService;
    private final ProductPhotoFindService productPhotoFindService;
    private final CustomerService customerService;
    private final SellerService sellerService;

    public DealService(DealRepository repository,
                       ProductService productService,
                       ProductPhotoFindService productPhotoFindService,
                       CustomerService customerService,
                       SellerService sellerService) {
        this.repository = repository;
        this.productService = productService;
        this.productPhotoFindService = productPhotoFindService;
        this.customerService = customerService;
        this.sellerService = sellerService;
    }

    @Transactional
    public void sendMessage(SendMessageRequest request) {
        Product product = productService.findProduct(request.productId());

        Deal deal = Deal.builder()
                .senderId(request.senderId())
                .receiverId(request.receiverId())
                .message(request.message())
                .product(product)
                .build();

        repository.save(deal);
    }

    public List<DealDto> getMessages(GetDealRequest request) {
        return repository.findAll()
                .stream()
                .filter(d -> (d.getSenderId().equals(request.userId()) || d.getReceiverId().equals(request.userId())) && d.getProduct().getId() == request.productId())
                .map(DealDto::convert)
                .collect(Collectors.toList());
    }

    public List<DealResponseDto> getDeals(String id) {

        final List<DealResponseDto> collect = repository.findAll()
                .stream()
                .filter(d -> d.getReceiverId().equals(id))
                .map(d -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(d.getProduct().getId());
                    ProductResponseDto product = new ProductResponseDto(ProductDto.convert(d.getProduct()), imageUrl);
                    return new DealResponseDto(d.getId(), product, d.getSenderId());
                })
                .collect(Collectors.toList());

        List<DealResponseDto> newList = new ArrayList<>();

        for (int i = 0; i < collect.size(); i++) {
            for (int j = 0; j < collect.size(); j++) {
                if (!((collect.get(i).product().getProduct().id() == collect.get(j).product().getProduct().id()) &&
                        collect.get(i).customerId().equals(collect.get(j).customerId()))) {
                    newList.add(collect.get(i));
                }
            }
        }

        return newList;
    }

    public Set<DealProductDto> getDeal(String id) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getReceiverId().equals(id))
                .filter(d -> !d.getProduct().isSold())
                .map(d -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(d.getProduct().getId());
                    return new DealProductDto(
                            d.getProduct().getId(),
                            d.getProduct().getShortDetails(),
                            d.getSenderId(),
                            customerService.findCustomer(d.getSenderId()).getFirstName() + " " +
                                    customerService.findCustomer(d.getSenderId()).getLastName(),
                            d.getProduct().getProductBrand().getBrandName(),
                            d.getProduct().getPrice(),
                            imageUrl);
                })
                .collect(Collectors.toSet());
    }



    public Set<CustomerDealDto> getDealsByCustomerId(String id) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getSenderId().equals(id))
                .map(d -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(d.getProduct().getId());
                    Seller seller = sellerService.findSeller(d.getReceiverId());
                    return new CustomerDealDto(
                            d.getProduct().getId(),
                            d.getProduct().getShortDetails(),
                            d.getProduct().getProductBrand().getBrandName(),
                            d.getProduct().getPrice(),
                            seller.getId(),
                            seller.getUsername(),
                            imageUrl);
                })
                .collect(Collectors.toSet());
    }
}
