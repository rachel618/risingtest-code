package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PostReviewReq {
    private double rating;
    private String imageUrl;
    private String review;

    public PostReviewReq() {
    }

    public PostReviewReq(double rating, String review) {
        this.rating = rating;
        this.review = review;
    }
}
