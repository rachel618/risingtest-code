package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.GetOneProductRes;
import com.example.demo.src.product.model.GetPopularRes;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetTodayProductRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("")
public class ProductController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProductProvider productProvider;

    @Autowired
    private final JwtService jwtService;

    public ProductController(ProductProvider productProvider, JwtService jwtService) {
        this.productProvider = productProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("/products")
    public BaseResponse<List<GetProductRes>> getProducts(){
        try{
            List<GetProductRes> getProductsRes = productProvider.getProducts();
            return new BaseResponse<>(SUCCESS,getProductsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 하나 정보 조회
     * @param productIdx
     * @return
     */
    @ResponseBody
    @GetMapping("/products/{productIdx}")
    public BaseResponse<GetOneProductRes> getOneProduct(@PathVariable("productIdx") int productIdx){
        try{
            GetOneProductRes getProductRes = productProvider.getProduct(productIdx);
            return new BaseResponse<>(SUCCESS_TO_GET_ONE_PRODUCT,getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 오늘의 딜 여러개 모여나오는 API
     * @return
     */
    @ResponseBody
    @GetMapping("/stores/today-deal")
    public BaseResponse<List<GetTodayProductRes>> getTodayDeal(){
        try{
            List<GetTodayProductRes> getTodayProductsRes = productProvider.getTodayDeal();
            return new BaseResponse<>(SUCCESS_TO_GET_TODAY_DEAL,getTodayProductsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/stores/popular-products")
    public BaseResponse<List<GetPopularRes>> getPopularProducts(){
        try{
            List<GetPopularRes> getPopularRes = productProvider.getPopularProducts();
            return new BaseResponse<>(SUCCESS_TO_GET_POPULAR_PRODUCTS,getPopularRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 별점 조회 API
//     * @param productIdx
//     * @return
//     */
//    @ResponseBody
//    @GetMapping("/products/{productIdx}/rating")
//    public BaseResponse<GetOneProductRes> getOneProduct(@PathVariable("productIdx") int productIdx){
//        try{
//            GetOneProductRes getProductRes = productProvider.getProduct(productIdx);
//            return new BaseResponse<>(SUCCESS_TO_GET_ONE_PRODUCT,getProductRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}
