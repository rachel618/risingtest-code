package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Order {
  private int orderIdx;
  private String ordererName;
  private String ordererEmail;
  private String ordererPhoneNum;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private String status;
  private boolean isMemberOrder;

    public Order() {
    }

    public Order(String ordererName, String ordererEmail, String ordererPhoneNum) {
        this.ordererName = ordererName;
        this.ordererEmail = ordererEmail;
        this.ordererPhoneNum = ordererPhoneNum;
    }
}
