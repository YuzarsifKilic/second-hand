package com.example.secondhand.service;

import com.example.secondhand.dto.TvDto;
import com.example.secondhand.dto.TvFilter;
import com.example.secondhand.exception.TvNotFoundException;
import com.example.secondhand.model.Pc;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.model.Tv;
import com.example.secondhand.repository.TvRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvService {

    private final TvRepository repository;
    private final ProductBrandService productBrandService;

    public TvService(TvRepository repository, ProductBrandService productBrandService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
    }

    public List<TvDto> getAllTv() {
        return repository.findAll()
                .stream()
                .map(TvDto::convert)
                .collect(Collectors.toList());
    }

    public TvDto findTvById(Long id) {
        return TvDto.convert(repository.findById(id)
                .orElseThrow(
                        () -> new TvNotFoundException("Tv didnt find by id : " + id)));
    }

    public List<TvDto> filterTv(TvFilter filter) {
        List<Tv> tvList = repository.findAll();
        if (!filter.getBrandName().isEmpty())
            tvList = brandFilter(tvList, filter.getBrandName());
        if (!filter.getScreenType().isEmpty())
            tvList = screenTypeFilter(tvList, filter.getScreenType());

        return tvList.stream()
                .map(TvDto::convert)
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
