package com.example.secondhand.service;

import com.example.secondhand.dto.filter.PhoneFilter;
import com.example.secondhand.dto.model.PhoneDto;
import com.example.secondhand.dto.model.PhoneResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreatePhoneRequest;
import com.example.secondhand.exception.PhoneNotFoundException;
import com.example.secondhand.model.Color;
import com.example.secondhand.model.Phone;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.model.Seller;
import com.example.secondhand.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneRepository repository;
    private final ProductBrandService productBrandService;
    private final ProductPhotoFindService productPhotoFindService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final ColorService colorService;

    public PhoneService(PhoneRepository repository,
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

    public List<ProductDto> getAllPhones() {
        return repository.findAll()
                .stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public PhoneResponseDto findPhoneById(Long id) {
        Phone phone = repository.findById(id)
                .orElseThrow(
                        () -> new PhoneNotFoundException("Phone didnt find by id : " + id));
        return new PhoneResponseDto(productService.findProductById(id), PhoneDto.convert(phone));
    }

    @Transactional
    public Long savePhone(CreatePhoneRequest request) {
        ProductBrand productBrand = productBrandService.findProductBrand(request.productBrandId());
        Seller seller = sellerService.findSeller(request.sellerId());
        Color color = colorService.findColorById(request.colorId());

        Phone phone = Phone.builder()
                .shortDetails(request.shortDetails())
                .price(request.price())
                .isSold(false)
                .details(request.details())
                .isPc(false)
                .isPhone(true)
                .isTv(false)
                .isGamingConsole(false)
                .isComputerAccessories(false)
                .productBrand(productBrand)
                .seller(seller)
                .brandModel(request.brandModel())
                .os(request.os())
                .screenSize(request.screenSize())
                .screenType(request.screenType())
                .ramSize(request.ramSize())
                .camera(request.camera())
                .frontCamera(request.frontCamera())
                .storage(request.storage())
                .color(color)
                .build();

        final Phone save = repository.save(phone);

        return save.getId();
    }

    public List<ProductResponseDto> phoneFilter(PhoneFilter filter) {
        List<Phone> phoneList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            phoneList = brandFilter(phoneList, filter.getBrandName());
        if (filter.getColorId() != null)
            phoneList = colorFilter(phoneList, filter.getColorId());
        if (!filter.getOsName().isEmpty())
            phoneList = osFilter(phoneList, filter.getOsName());
        if (filter.getCameraMin() != null && filter.getCameraMax() != null)
            phoneList = cameraFilter(phoneList, filter.getCameraMin(), filter.getCameraMax());
        if (filter.getFrontCameraMin() != null && filter.getFrontCameraMax() != null)
            phoneList = frontCameraFilter(phoneList, filter.getFrontCameraMin(), filter.getFrontCameraMax());

        return phoneList.stream()
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    private List<Phone> brandFilter(List<Phone> phoneList, String brand) {
        ProductBrand productBrand = productBrandService.getProduct(brand);
        return phoneList.stream()
                .filter(p -> p.getProductBrand().getBrandName().equals(productBrand.getBrandName()))
                .collect(Collectors.toList());
    }

    private List<Phone> osFilter(List<Phone> phoneList, String os) {
        return phoneList.stream()
                .filter(p -> p.getOs().equals(os))
                .collect(Collectors.toList());
    }

    private List<Phone> colorFilter(List<Phone> phoneList, int colorId) {
        return phoneList.stream()
                .filter(p -> p.getColor().getId() == colorId)
                .collect(Collectors.toList());
    }

    private List<Phone> cameraFilter(List<Phone> phoneList, int min, int max) {
        return phoneList.stream()
                .filter(p -> p.getCamera() > min && p.getCamera() < max)
                .collect(Collectors.toList());
    }

    private List<Phone> frontCameraFilter(List<Phone> phoneList, int min, int max) {
        return phoneList.stream()
                .filter(p -> p.getFrontCamera() > min && p.getFrontCamera() < max)
                .collect(Collectors.toList());
    }


}
