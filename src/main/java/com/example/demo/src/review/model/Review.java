package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    // 리뷰 고유번호
    private Integer reviewIdx;
    // 사용자 고유번호
    private Integer userIdx;

    // 제품 고유번호
    private Integer productIdx;

    // 리뷰내용
    private String review;

    // 별점
    private Double rating;

    // 리뷰 사진
    private String imageUrl;

    // 리뷰종류 "PHOTO","NOPHOTO"
    private String reviewYype;

    // 상태 "POSTED,DELETED"
    private String status;

    // 생성시간
    private Timestamp createdAt;

    // 수정시간
    private Timestamp updatedAt;
}
