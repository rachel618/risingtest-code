package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetFirstProductCategoryRes;
import com.example.demo.src.category.model.GetSecondProductCategoryRes;
import com.example.demo.src.category.model.GetThirdProductCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/stores")
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryProvider categoryProvider;

    public CategoryController(CategoryProvider categoryProvider) {
        this.categoryProvider = categoryProvider;
    }

    @ResponseBody
    @GetMapping("/first-categories") //
    public BaseResponse<List<GetFirstProductCategoryRes>> getFirstCategories() {
        try{
            List<GetFirstProductCategoryRes> getCategoriesRes = categoryProvider.getFirstCategories();
            return new BaseResponse<>(SUCCESS_TO_GET_FIRST_CATEGORIES,getCategoriesRes);
            // Get Users

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{firstCategoryIdx}/second-categories") //
    public BaseResponse<List<GetSecondProductCategoryRes>> getSecondCategories(@PathVariable("firstCategoryIdx")int firstCategoryIdx) {
        try{
            List<GetSecondProductCategoryRes> getCategoriesRes = categoryProvider.getSecondCategories(firstCategoryIdx);
            return new BaseResponse<>(SUCCESS_TO_GET_SECOND_CATEGORIES,getCategoriesRes);


        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{firstCategoryIdx}/{secondCategoryIdx}/third-categories") //
    public BaseResponse<List<GetThirdProductCategoryRes>> getThirdCategories(@PathVariable("firstCategoryIdx")int firstCategoryIdx, @PathVariable("secondCategoryIdx")int secondCategoryIdx) {
        try{
            List<GetThirdProductCategoryRes> getCategoriesRes = categoryProvider.getThirdCategories(firstCategoryIdx,secondCategoryIdx);
            return new BaseResponse<>(SUCCESS_TO_GET_THIRD_CATEGORIES,getCategoriesRes);


        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//    @ResponseBody
//    @GetMapping("/second-categories") // (GET) 127.0.0.1:9000/app/users
//    public BaseResponse<List<GetSecondCategoryRes>> getSecondCategories((@RequestParam) {
//        try{
//            List<GetSecondCategoryRes> getCategoriesRes = categoryProvider.getSecondCategories();
//            return new BaseResponse<>(SUCCESS,getCategoriesRes);
//            // Get Users
//
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}
