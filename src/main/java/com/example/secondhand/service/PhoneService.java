package com.example.secondhand.service;

import com.example.secondhand.dto.PhoneDto;
import com.example.secondhand.dto.PhoneFilter;
import com.example.secondhand.exception.PhoneNotFoundException;
import com.example.secondhand.model.Phone;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneRepository repository;
    private final ProductBrandService productBrandService;

    public PhoneService(PhoneRepository repository, ProductBrandService productBrandService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
    }

    public List<PhoneDto> getAllPhones() {
        return repository.findAll()
                .stream()
                .map(PhoneDto::convert)
                .collect(Collectors.toList());
    }

    public PhoneDto findPhoneById(Long id) {
        return PhoneDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new PhoneNotFoundException("Phone didnt find by id : " + id)));
    }

    public List<PhoneDto> phoneFilter(PhoneFilter filter) {
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
                .map(PhoneDto::convert)
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
