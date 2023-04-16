package com.example.secondhand.service;

import com.example.secondhand.dto.model.GamingConsoleDto;
import com.example.secondhand.dto.filter.GamingConsoleFilter;
import com.example.secondhand.exception.GamingConsoleNotFoundException;
import com.example.secondhand.model.GamingConsole;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.repository.GamingConsoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamingConsoleService {

    private final GamingConsoleRepository repository;
    private final ProductBrandService productBrandService;

    public GamingConsoleService(GamingConsoleRepository repository, ProductBrandService productBrandService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
    }

    public List<GamingConsoleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(GamingConsoleDto::convert)
                .collect(Collectors.toList());
    }

    public GamingConsoleDto findGamingConsoleById(Long id) {
        return GamingConsoleDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new GamingConsoleNotFoundException("Gaming Console didnt find by id : " + id)));
    }

    public List<GamingConsoleDto> filter(GamingConsoleFilter filter) {
        List<GamingConsole> gamingConsoleList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            gamingConsoleList = brandFilter(gamingConsoleList, filter.getBrandName());
        if (filter.getColorId() != null)
            gamingConsoleList = colorFilter(gamingConsoleList, filter.getColorId());
        return gamingConsoleList.stream()
                .map(GamingConsoleDto::convert)
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
