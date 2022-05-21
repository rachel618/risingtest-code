package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.GetFirstProductCategoryRes;
import com.example.demo.src.category.model.GetSecondProductCategoryRes;
import com.example.demo.src.category.model.GetThirdProductCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CategoryProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryDao categoryDao;

    public CategoryProvider(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public List<GetFirstProductCategoryRes> getFirstCategories() throws BaseException {
        try{
            List<GetFirstProductCategoryRes> getCategoryRes = categoryDao.getFirstCategories();
            return getCategoryRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSecondProductCategoryRes> getSecondCategories(int firstCategoryIdx) throws BaseException {
        try{
            List<GetSecondProductCategoryRes> getCategoryRes = categoryDao.getSecondCategories(firstCategoryIdx);
            return getCategoryRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetThirdProductCategoryRes> getThirdCategories(int firstCategoryIdx, int secondCategoryIdx) throws BaseException {
        try{
            List<GetThirdProductCategoryRes> getCategoryRes = categoryDao.getThirdCategories(firstCategoryIdx,secondCategoryIdx);
            return getCategoryRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
