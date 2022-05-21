package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetProductReviewRes {
    private String nickname;
    private String createdAt;
    private double rating;
    private String imageUrl;
    private String review;
}
