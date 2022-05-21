package com.example.demo.src.brand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BrandDao brandDao;

    @Autowired
    public BrandProvider(BrandDao brandDao) {
        this.brandDao = brandDao;
    }
}
