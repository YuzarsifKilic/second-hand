package com.example.secondhand.service;

import com.example.secondhand.dto.model.PcDto;
import com.example.secondhand.dto.filter.PcFilter;
import com.example.secondhand.dto.model.PcResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreatePcRequest;
import com.example.secondhand.exception.PcNotFoundException;
import com.example.secondhand.model.*;
import com.example.secondhand.repository.PcRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PcService {

    private final PcRepository repository;
    private final ProductBrandService productBrandService;
    private final ProductPhotoService productPhotoService;
    private final ProductPhotoFindService productPhotoFindService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final CpuService cpuService;
    private final GpuService gpuService;

    public PcService(PcRepository repository,
                     ProductBrandService productBrandService,
                     ProductPhotoService productPhotoService,
                     ProductPhotoFindService productPhotoFindService,
                     ProductService productService,
                     SellerService sellerService,
                     CpuService cpuService,
                     GpuService gpuService) {
        this.repository = repository;
        this.productBrandService = productBrandService;
        this.productPhotoService = productPhotoService;
        this.productPhotoFindService = productPhotoFindService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.cpuService = cpuService;
        this.gpuService = gpuService;
    }

    public PcResponseDto findPcById(Long id) {
        Pc pc = repository.findById(id)
                .orElseThrow(
                        () -> new PcNotFoundException("Pc didnt find by id : " + id));

        return new PcResponseDto(productService.findProductById(pc.getId()), PcDto.convert(pc));
    }

    public List<ProductDto> getAll() {
        List<Long> idList = repository.findAll()
                .stream()
                .map(Product::getId)
                .toList();

        final List<String> photoUrlByProductIdList = productPhotoService.findPhotoUrlByProductIdList(idList);

        return repository.findAll()
                .stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long savePc(CreatePcRequest request) {
        ProductBrand productBrand = productBrandService.findProductBrand(request.productBrandId());
        Seller seller = sellerService.findSeller(request.sellerId());
        Cpu cpu = cpuService.findCpuById(request.cpuId());
        Gpu gpu = gpuService.findGpuById(request.gpuId());

        Pc pc = Pc.builder()
                .shortDetails(request.shortDetails())
                .price(request.price())
                .isSold(false)
                .details(request.details())
                .productBrand(productBrand)
                .isPc(true)
                .isPhone(false)
                .isTv(false)
                .isGamingConsole(false)
                .isComputerAccessories(false)
                .seller(seller)
                .brandModel(request.brandModel())
                .cpu(cpu)
                .cpuSpeed(request.cpuSpeed())
                .gpu(gpu)
                .gpuSize(request.gpuSize())
                .ramSize(request.ramSize())
                .ramSpeed(request.ramSpeed())
                .screenSize(request.screenSize())
                .modelYear(request.modelYear())
                .resolution(request.resolution())
                .build();

        final Pc save = repository.save(pc);

        return save.getId();
    }

    public List<ProductResponseDto> filterPc(PcFilter pcFilter) {
        List<Pc> pcList = repository.findAll();
        if (!pcFilter.getBrandName().isEmpty())
            pcList = brandFilter(pcList, pcFilter.getBrandName());
        if (pcFilter.getCpuId() != null)
            pcList = cpuFilter(pcList, pcFilter.getCpuId());
        if (pcFilter.getGpuId() != null)
            pcList = gpuFilter(pcList, pcFilter.getGpuId());
        if (pcFilter.getRamSize() != null)
            pcList = ramSizeFilter(pcList, pcFilter.getRamSize());

        return pcList.stream()
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    private Product pcToProduct(Pc from) {
        return Product.builder()
                .id(from.getId())
                .shortDetails(from.getShortDetails())
                .price(from.getPrice())
                .isSold(from.isSold())
                .details(from.getDetails())
                .isPc(from.isPc())
                .isPhone(from.isPhone())
                .isTv(from.isTv())
                .isGamingConsole(from.isGamingConsole())
                .isComputerAccessories(from.isComputerAccessories())
                .build();
    }

    private List<Pc> brandFilter(List<Pc> pcList, String brand) {
        ProductBrand productBrand = productBrandService.getProduct(brand);
        return pcList.stream()
                .filter(p -> p.getProductBrand().getBrandName().equals(productBrand.getBrandName()))
                .collect(Collectors.toList());
    }

    private List<Pc> cpuFilter(List<Pc> pcList, int id) {
        return pcList.stream()
                .filter(p -> p.getCpu().getId() == id)
                .collect(Collectors.toList());
    }

    private List<Pc> gpuFilter(List<Pc> pcList, int id) {
        return pcList.stream()
                .filter(p -> p.getGpu().getId() == id)
                .collect(Collectors.toList());
    }

    private List<Pc>  ramSizeFilter(List<Pc> pcList, int ramSize) {
        return pcList.stream()
                .filter(p -> p.getRamSize() == ramSize)
                .collect(Collectors.toList());
    }

}
