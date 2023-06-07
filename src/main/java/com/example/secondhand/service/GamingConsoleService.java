package com.example.secondhand.service;

import com.example.secondhand.dto.filter.GamingConsoleFilter;
import com.example.secondhand.dto.model.GamingConsoleDto;
import com.example.secondhand.dto.model.GamingConsoleResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreateGamingConsoleRequest;
import com.example.secondhand.exception.GamingConsoleNotFoundException;
import com.example.secondhand.model.Color;
import com.example.secondhand.model.GamingConsole;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.model.Seller;
import com.example.secondhand.repository.GamingConsoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamingConsoleService {

    private final GamingConsoleRepository repository;
    private final ProductBrandService productBrandService;
    private final ProductPhotoFindService productPhotoFindService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final ColorService colorService;

    public GamingConsoleService(GamingConsoleRepository repository,
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

    public GamingConsoleResponseDto findGamingConsoleById(Long id) {
        GamingConsole gamingConsole = repository.findById(id)
                .orElseThrow(
                        () -> new GamingConsoleNotFoundException("Gaming Console didnt find by id : " + id));
        return new GamingConsoleResponseDto(productService.findProductById(id), GamingConsoleDto.convert(gamingConsole));
    }

    @Transactional
    public Long saveGamingConsole(CreateGamingConsoleRequest request) {
        ProductBrand productBrand = productBrandService.findProductBrand(request.productBrandId());
        Seller seller = sellerService.findSeller(request.sellerId());
        Color color = colorService.findColorById(request.colorId());

        GamingConsole gamingConsole = GamingConsole.builder()
                .shortDetails(request.shortDetails())
                .price(request.price())
                .isSold(false)
                .details(request.details())
                .isPc(false)
                .isPhone(false)
                .isTv(false)
                .isGamingConsole(true)
                .isComputerAccessories(false)
                .productBrand(productBrand)
                .seller(seller)
                .brandModel(request.brandModel())
                .storage(request.storage())
                .color(color)
                .build();

        final GamingConsole save = repository.save(gamingConsole);

        return save.getId();
    }

    public List<ProductResponseDto> filter(GamingConsoleFilter filter) {
        List<GamingConsole> gamingConsoleList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            gamingConsoleList = brandFilter(gamingConsoleList, filter.getBrandName());
        if (filter.getColorId() != null)
            gamingConsoleList = colorFilter(gamingConsoleList, filter.getColorId());
        return gamingConsoleList.stream()
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    private List<GamingConsole> brandFilter(List<GamingConsole> gamingConsoleList, String brand) {
        ProductBrand productBrand = productBrandService.getProduct(brand);
        return gamingConsoleList.stream()
                .filter(g -> g.getProductBrand().getBrandName().equals(productBrand.getBrandName()))
                .collect(Collectors.toList());
    }

    private List<GamingConsole> colorFilter(List<GamingConsole> gamingConsoleList, int colorId) {
        return gamingConsoleList.stream()
                .filter(g -> g.getColor().getId() == colorId)
                .collect(Collectors.toList());
    }
}
