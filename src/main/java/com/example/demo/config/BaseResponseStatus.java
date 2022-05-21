package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */

    //1100~ : 유저관련
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    SUCCESS_TO_SIGNIN(true, 1100, "로그인에 성공하였습니다."),
    SUCCESS_TO_SIGNUP(true, 1101, "회원가입에 성공하였습니다."),
    SUCCESS_TO_GET_PROFILE(true, 1102, "프로필 조회 성공하였습니다."),
    SUCCESS_TO_PATCH_USER_PWD(true, 1103, "비밀번호 변경에 성공하였습니다."),


    //1200~ : 상품관련
    SUCCESS_TO_GET_TODAY_DEAL(true, 1200, "오늘의 딜 조회 요청 성공"),
    SUCCESS_TO_GET_ONE_PRODUCT(true, 1201, "상품 페이지로 이동 성공"),
    SUCCESS_TO_GET_POPULAR_PRODUCTS(true, 1202, "인기 상품 조회에 성공하였습니다."),


    //1300~ : 쿠폰 관련
    SUCCESS_TO_GET_MY_COUPON(true, 1300, "내 쿠폰 조회에 성공하였습니다."),
    SUCCESS_TO_GET_COUPONS(true, 1301, "사용가능한 쿠폰 조회에 성공하였습니다."),
    SUCCESS_TO_RECEIVE_COUPONS(true, 1302, "쿠폰 받기에 성공하였습니다."),

    //1400~: 리뷰관련
    SUCCESS_TO_GET_REVIEWS(true, 1400, "상품 리뷰 조회에 성공하였습니다."),
    SUCCESS_TO_POST_REVIEW(true, 1401, "상품 리뷰 작성에 성공하였습니다."),
    SUCCESS_TO_DELETE_REVIEW(true, 1402, "상품 리뷰 삭제에 성공하였습니다."),

    //1500~: 카테고리 관련
    SUCCESS_TO_GET_FIRST_CATEGORIES(true, 1500, "첫번째 상품 카테고리 조회에 성공하였습니다."),
    SUCCESS_TO_GET_SECOND_CATEGORIES(true, 1501, "두번째 상품 카테고리 조회에 성공하였습니다."),
    SUCCESS_TO_GET_THIRD_CATEGORIES(true, 1502, "세번쨰 상품 카테고리 조회에 성공하였습니다."),
    //1600~: 주문 관련
    SUCCESS_TO_POST_ORDER(true,1600,"주문완료되었습니다."),
    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    FAILED_TO_MODIFY_PASSWORD(false,2011,"비밀번호가 일치하지 않습니다."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_DISAGREE_TOS(false,2018,"이용약관 동의해야 합니다."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),
    FAILED_TO_GET_COUPON(false,3015,"쿠폰 받기에 실패하였습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
