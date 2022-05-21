package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderProductOptionReq {
    private int productIdx;
    private int productCnt;
    private int optionIdx;

    public PostOrderProductOptionReq(int productIdx, int productCnt) {
        this.productIdx = productIdx;
        this.productCnt = productCnt;
    }

    public PostOrderProductOptionReq() {
    }
}
