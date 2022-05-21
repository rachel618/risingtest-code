package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.GetOneProductRes;
import com.example.demo.src.product.model.GetPopularRes;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetTodayProductRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.jar.JarEntry;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ProductProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final JwtService jwtService;

    @Autowired
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService;
    }

    public List<GetProductRes> getProducts() throws BaseException {
        try{
            List<GetProductRes> getProductRes = productDao.getProducts();
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetOneProductRes getProduct(int productIdx) throws BaseException {
        try{
            GetOneProductRes getProductRes = productDao.getProduct(productIdx);
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetTodayProductRes> getTodayDeal() throws BaseException{
        try{
            List<GetTodayProductRes> getTodayProductsRes= productDao.getTodayDeal();
            return getTodayProductsRes;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetPopularRes> getPopularProducts() throws BaseException{
        try{
            List<GetPopularRes> getPopularRes= productDao.getPopularProducts();
            return getPopularRes;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
