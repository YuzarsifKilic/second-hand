package com.example.secondhand.controller;

import com.example.secondhand.service.ProductPhotoService;
import com.example.secondhand.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photo")
@CrossOrigin("*")
public class ProductPhotoController {

    private final ProductPhotoService service;

    public ProductPhotoController(ProductPhotoService service) {
        this.service = service;
    }

    @PostMapping(
            path = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("id") Long id,
                                       @RequestParam("file") MultipartFile file) {
        service.uploadProductPhoto(id, file);
    }

    @GetMapping("/{id}/{url}/download")
    public byte[] downloadImageByUrl(@PathVariable Long id, @PathVariable String url) {
        return service.downloadImageByUrl(id, url);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getImageUrlById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findPhotoByProductId(id));
    }

}
