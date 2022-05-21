package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class FirstProductCategory {
    private int firstCategoryIdx;
    private String firstCategoryName;
    private String imageUrl;
    private Time createdAt;
    private Time updatedAt;
}
