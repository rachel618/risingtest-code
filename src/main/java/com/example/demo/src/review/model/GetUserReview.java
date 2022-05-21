package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class GetUserReview {
    private String storeName;
    private String menuName;
    private Time createdAt;
    private int rating;
    private String photoUrl;
    private String comment;

    public GetUserReview(Time createdAt, int rating, String photoUrl, String comment) {
        this.createdAt = createdAt;
        this.rating = rating;
        this.photoUrl = photoUrl;
        this.comment = comment;
    }
}
