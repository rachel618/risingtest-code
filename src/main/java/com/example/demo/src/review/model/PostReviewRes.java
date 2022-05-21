package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewRes {
    private String nickname;
    private String productName;
    private double rating;
    private String review;
    private String imageUrl;
    private String createdAt;
}
