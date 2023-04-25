package com.example.secondhand.service;

import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.filter.TvFilter;
import com.example.secondhand.dto.model.TvDto;
import com.example.secondhand.dto.model.TvResponseDto;
import com.example.secondhand.dto.request.CreateTvRequest;
import com.example.secondhand.exception.TvNotFoundException;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.model.Seller;
import com.example.secondhand.model.Tv;
import com.example.secondhand.repository.TvRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvService {

    private final TvRepository repository;
    private final ProductBrandService productBrandService;
    private final ProductService productService;
    private final SellerService sellerService;

    public TvService(TvRepository repository,
                     ProductBrandService productBrandService,
                     ProductService productService,
                     SellerService sellerService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    public List<ProductDto> getAllTv() {
        return repository.findAll()
                .stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public TvResponseDto findTvById(Long id) {
        Tv tv = repository.findById(id)
                .orElseThrow(
                        () -> new TvNotFoundException("Tv didnt find by id : " + id));
        return new TvResponseDto(productService.findProductById(id), TvDto.convert(tv));
    }

    @Transactional
    public void saveTv(CreateTvRequest request) {
        ProductBrand productBrand = productBrandService.findProductBrand(request.productBrandId());
        Seller seller = sellerService.findSeller(request.sellerId());

        Tv tv = Tv.builder()
                .shortDetails(request.shortDetails())
                .price(request.price())
                .isSold(false)
                .details(request.details())
                .isPc(false)
                .isPhone(false)
                .isTv(true)
                .isGamingConsole(false)
                .isComputerAccessories(false)
                .productBrand(productBrand)
                .seller(seller)
                .brandModel(request.brandModel())
                .screenSize(request.screenSize())
                .resolution(request.resolution())
                .screenType(request.screenType())
                .quality(request.quality())
                .build();

        repository.save(tv);
    }

    public List<ProductDto> filterTv(TvFilter filter) {
        List<Tv> tvList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            tvList = brandFilter(tvList, filter.getBrandName());
        if (!filter.getScreenType().isEmpty())
            tvList = screenTypeFilter(tvList, filter.getScreenType());

        return tvList.stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    private List<Tv> brandFilter(List<Tv> tvList, String brand) {
        ProductBrand productBrand = productBrandService.getProduct(brand);
        return tvList.stream()
                .filter(t -> t.getProductBrand().getBrandName().equals(productBrand.getBrandName()))
                .collect(Collectors.toList());
    }

    private List<Tv> screenTypeFilter(List<Tv> tvList, String screenType) {
        return tvList.stream()
                .filter(t -> t.getScreenType().equals(screenType))
                .collect(Collectors.toList());
    }
}
