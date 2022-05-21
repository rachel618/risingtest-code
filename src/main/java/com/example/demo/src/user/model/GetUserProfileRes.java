package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetUserProfileRes {
    private String nickname;
    private int numOfFollowers;
    private int numOfFollowing;
    private int numOfScraps;
    private int numOfLikes;
    private int ongoingOrders;
    private int numOfCoupons;
    private int point;
    private String profileImage;

    public GetUserProfileRes(String nickname, int numOfFollowers, int numOfFollowing, int numOfScraps, int numOfLikes, int point, String profileImage) {
        this.nickname = nickname;
        this.numOfFollowers = numOfFollowers;
        this.numOfFollowing = numOfFollowing;
        this.numOfScraps = numOfScraps;
        this.numOfLikes = numOfLikes;
        this.point = point;
        this.profileImage = profileImage;
    }
}
