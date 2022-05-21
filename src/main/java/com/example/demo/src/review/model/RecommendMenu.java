package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecommendMenu {
    private int recommendMenuIdx;
    private String menuName;
    private String recommend;
    private int orderIdx;

    public RecommendMenu(String menuName, String recommend) {
        this.menuName=menuName;
        this.recommend = recommend;
    }
}
