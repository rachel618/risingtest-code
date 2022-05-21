package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.order.model.PostOrderRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final OrderProvider orderProvider;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final JwtService jwtService;

    public OrderController(OrderProvider orderProvider, OrderService orderService, JwtService jwtService) {
        this.orderProvider = orderProvider;
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

//    /**
//     * 주문내역조회
//     * @param userIdx
//     * @return GetOrderRes
//     */
//    @ResponseBody
//    @GetMapping("/{userIdx}")
//    public BaseResponse<List<GetOrderRes>> getOrders(@PathVariable("userIdx")int userIdx){
//        try{
//            List<GetOrderRes> getOrderRes = orderProvider.getOrders(userIdx);
//            return new BaseResponse<>(getOrderRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }


    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostOrderRes> createOrder(@RequestParam(defaultValue = "0")int userIdx,@RequestBody PostOrderReq postOrderReq){
        try{
            PostOrderRes postOrderRes;
//            if(userIdx == 0){
                postOrderRes=orderService.createMemberOrder(userIdx,postOrderReq);
//            }
//            else
//                postOrderRes=orderService.createNonMemberOrder(postOrderReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS_TO_POST_ORDER,postOrderRes);
        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//
//    @ResponseBody
//    @GetMapping("/{userIdx}")
//    public BaseResponse<PostOrderRes> getOrder(@RequestParam(defaultValue = "0")int userIdx){
//        try{
//            PostOrderRes postOrderRes;
////            if(userIdx == 0){
//            postOrderRes=orderService.getMemberOrder(userIdx);
////            }
////            else
////                postOrderRes=orderService.createNonMemberOrder(postOrderReq);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS_TO_POST_ORDER,postOrderRes);
//        }catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}
