package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int userIdx;
    private String nickname;
    private String userEmail;
    private String password;
    private int numOfFollowers;
    private int numOfFollowing;
    private int numOfScraps;
    private int numOfLikes;
    private int point;
    private String profileImage;
}
