package com.example.demo.src.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private final BrandProvider brandProvider;

    public BrandController(BrandProvider brandProvider) {
        this.brandProvider = brandProvider;
    }


}
