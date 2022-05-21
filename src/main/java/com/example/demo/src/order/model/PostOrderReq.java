package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sun.jvm.hotspot.debugger.SymbolLookup;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PostOrderReq {
    private String ordererName;
    private String ordererEmail;
    private String ordererPhoneNum;
    private String addressName;
    private String receiverName;
    private String contact;
    private String address;
    private String request;
    private Boolean isDefaultAddress;
    private List<PostOrderProductOptionReq> products;
//    private int couponIdx;
    private int point;
    private String payment;
    private String cardType;
    private int installment;

    public PostOrderReq() {
    }

    public PostOrderReq(String ordererName, String ordererEmail, String ordererPhoneNum, String addressName, String receiverName, String contact, String address, String request, String payment) {
        this.ordererName = ordererName;
        this.ordererEmail = ordererEmail;
        this.ordererPhoneNum = ordererPhoneNum;
        this.addressName = addressName;
        this.receiverName = receiverName;
        this.contact = contact;
        this.address = address;
        this.request = request;
        this.payment = payment;
    }
}

