package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ProductOption {
    private int optionIdx;
    private int productIdx;
    private String optionName;
    private int optionprice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String status;
}
