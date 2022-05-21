package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String nickname;
    private String userEmail;
    private String password;
    private int numOfFollowers;
    private int numOfFollowing;
    private int numOfScraps;
    private int numOfLikes;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;
    private int notification;
    private int point;
    private String profileImage;

    public User(String nickname, String userEmail, String password, int notification) {
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.password = password;
        this.notification = notification;
    }
}
