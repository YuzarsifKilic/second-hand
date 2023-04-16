package com.example.secondhand.service;

import com.example.secondhand.dto.filter.ComputerAccessoriesFilter;
import com.example.secondhand.dto.model.ComputerAccessoriesDto;
import com.example.secondhand.exception.ComputerAccessoriesNotFoundException;
import com.example.secondhand.model.ComputerAccessories;
import com.example.secondhand.model.GamingConsole;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.repository.ComputerAccessoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerAccessoriesService {

    private final ComputerAccessoriesRepository repository;
    private final ProductBrandService productBrandService;

    public ComputerAccessoriesService(ComputerAccessoriesRepository repository, ProductBrandService productBrandService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
    }

    public List<ComputerAccessoriesDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ComputerAccessoriesDto::convert)
                .collect(Collectors.toList());
    }

    public ComputerAccessoriesDto findComputerAccessoriesById(Long id) {
        return ComputerAccessoriesDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new ComputerAccessoriesNotFoundException("Computer Accessory didnt find by id : " + id)));
    }

    public List<ComputerAccessoriesDto> filter(ComputerAccessoriesFilter filter) {
        List<ComputerAccessories> computerAccessoriesList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            computerAccessoriesList = brandFilter(computerAccessoriesList, filter.getBrandName());
        if (filter.getColorId() != null)
            computerAccessoriesList = colorFilter(computerAccessoriesList, filter.getColorId());
        if (!filter.getConnectivityTechnology().isEmpty())
            computerAccessoriesList = connectivityTechnologyFilter(computerAccessoriesList, filter.getConnectivityTechnology());

        return computerAccessoriesList.stream()
                .map(ComputerAccessoriesDto::convert)
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
