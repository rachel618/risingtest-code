package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class GetFirstProductCategoryRes {
    private String categoryName;
    private String imageUrl;
    public GetFirstProductCategoryRes() {
    }
}
