package com.example.demo.src.category;

import com.example.demo.src.category.model.GetFirstProductCategoryRes;
import com.example.demo.src.category.model.GetSecondProductCategoryRes;
import com.example.demo.src.category.model.GetThirdProductCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.util.calendar.CalendarSystem;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetFirstProductCategoryRes> getFirstCategories(){
        String getCategoriesQuery="select categoryName,imageUrl from FirstProductCategory";

        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs,rowNum) -> new GetFirstProductCategoryRes(
                        rs.getString("categoryName"),
                        rs.getString("imageUrl"))
        );
    }

    public List<GetSecondProductCategoryRes> getSecondCategories(int firstCategoryIdx){
        String getCategoriesQuery="select FirstProductCategory.categoryName as firstCategoryName,SecondProductCategory.categoryName as secondCategoryName" +
                ",SecondProductCategory.imageUrl as imageUrl " +
                "from FirstProductCategory,SecondProductCategory " +
                "where FirstProductCategory.firstCategoryIdx=SecondProductCategory.firstCategoryIdx and SecondProductCategory.firstCategoryIdx=?";
        int getCategoriesParam=firstCategoryIdx;
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs,rowNum) -> new GetSecondProductCategoryRes(
                        rs.getString("firstCategoryName"),
                        rs.getString("secondCategoryName"),
                        rs.getString("imageUrl"))
                ,getCategoriesParam);
    }

    public List<GetThirdProductCategoryRes> getThirdCategories(int firstCategoryIdx, int secondCategoryIdx){
        String getCategoriesQuery="select SecondProductCategory.categoryName as secondCategoryName,ThirdProductCategory.categoryName as thirdCategoryName\n" +
                "from ThirdProductCategory,SecondProductCategory,FirstProductCategory\n" +
                "where SecondProductCategory.secondCategoryIdx=ThirdProductCategory.secondCategoryIdx and ThirdProductCategory.secondCategoryIdx=?\n" +
                "  and FirstProductCategory.firstCategoryIdx=SecondProductCategory.firstCategoryIdx and SecondProductCategory.firstCategoryIdx=?;";
        System.out.println("sdv");
        Object[] getCategoriesParam=new Object[]{secondCategoryIdx,firstCategoryIdx};
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs,rowNum) -> new GetThirdProductCategoryRes(
                        rs.getString("secondCategoryName"),
                        rs.getString("thirdCategoryName"))
                ,getCategoriesParam);
    }
}
