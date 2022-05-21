package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String userEmail;
    private String password;
    private String nickname;
    private int overFourteen;
    private int tos; // terms of use, 이용약관
    private int agreeTos;
    private int notification;

}
