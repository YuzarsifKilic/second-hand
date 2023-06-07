package com.example.secondhand.service;

import com.example.secondhand.dto.filter.ComputerAccessoriesFilter;
import com.example.secondhand.dto.model.ComputerAccessoriesDto;
import com.example.secondhand.dto.model.ComputerAccessoriesResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreateComputerAccessoriesRequest;
import com.example.secondhand.exception.ComputerAccessoriesNotFoundException;
import com.example.secondhand.model.Color;
import com.example.secondhand.model.ComputerAccessories;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.model.Seller;
import com.example.secondhand.repository.ComputerAccessoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerAccessoriesService {

    private final ComputerAccessoriesRepository repository;
    private final ProductBrandService productBrandService;
    private final ProductPhotoFindService productPhotoFindService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final ColorService colorService;

    public ComputerAccessoriesService(ComputerAccessoriesRepository repository,
                                      ProductBrandService productBrandService,
                                      ProductPhotoFindService productPhotoFindService,
                                      ProductService productService,
                                      SellerService sellerService,
                                      ColorService colorService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
        this.productPhotoFindService = productPhotoFindService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.colorService = colorService;
    }

    public List<ProductDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public ComputerAccessoriesResponseDto findComputerAccessoriesById(Long id) {
        ComputerAccessories computerAccessories = repository.findById(id)
                .orElseThrow(
                        () -> new ComputerAccessoriesNotFoundException("Computer Accessory didnt find by id : " + id));
        return new ComputerAccessoriesResponseDto(productService.findProductById(id), ComputerAccessoriesDto.convert(computerAccessories));
    }

    @Transactional
    public Long saveComputerAccessories(CreateComputerAccessoriesRequest request) {
        ProductBrand productBrand = productBrandService.findProductBrand(request.productBrandId());
        Seller seller = sellerService.findSeller(request.sellerId());
        Color color = colorService.findColorById(request.colorId());

        ComputerAccessories computerAccessories = ComputerAccessories.builder()
                .shortDetails(request.shortDetails())
                .price(request.price())
                .isSold(false)
                .details(request.details())
                .isPc(false)
                .isPhone(false)
                .isTv(false)
                .isGamingConsole(false)
                .isComputerAccessories(true)
                .productBrand(productBrand)
                .seller(seller)
                .brandModel(request.brandModel())
                .connectivityTechnology(request.connectivityTechnology())
                .color(color)
                .build();

        final ComputerAccessories save = repository.save(computerAccessories);

        return save.getId();
    }

    public List<ProductResponseDto> filter(ComputerAccessoriesFilter filter) {
        List<ComputerAccessories> computerAccessoriesList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            computerAccessoriesList = brandFilter(computerAccessoriesList, filter.getBrandName());
        if (filter.getColorId() != null)
            computerAccessoriesList = colorFilter(computerAccessoriesList, filter.getColorId());
        if (!filter.getConnectivityTechnology().isEmpty())
            computerAccessoriesList = connectivityTechnologyFilter(computerAccessoriesList, filter.getConnectivityTechnology());

        return computerAccessoriesList.stream()
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    private List<ComputerAccessories> brandFilter(List<ComputerAccessories> computerAccessoriesList, String brand) {
        ProductBrand productBrand = productBrandService.getProduct(brand);
        return computerAccessoriesList.stream()
                .filter(c -> c.getProductBrand().getBrandName().equals(productBrand.getBrandName()))
                .collect(Collectors.toList());
    }

    private List<ComputerAccessories> connectivityTechnologyFilter(List<ComputerAccessories> computerAccessoriesList,
                                                                   String connectivityTechnology) {
        return computerAccessoriesList.stream()
                .filter(c -> c.getConnectivityTechnology().equals(connectivityTechnology))
                .collect(Collectors.toList());
    }

    private List<ComputerAccessories> colorFilter(List<ComputerAccessories> computerAccessoriesList, int colorId) {
        return computerAccessoriesList.stream()
                .filter(c -> c.getColor().getId() == colorId)
                .collect(Collectors.toList());
    }


}
