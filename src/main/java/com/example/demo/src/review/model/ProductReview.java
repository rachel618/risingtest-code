package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ProductReview {
    private int reviewIdx;
    private int productIdx;
    private String review;
    private String imageUrl;
    private double rating;
    private int reviewType;
    private int userIdx;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
