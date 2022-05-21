package com.example.demo.src.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BrandDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getBrandName(int brandIdx){
        String getBrandNameQuery="select brandName from Brand where brandIdx="+ brandIdx;
        return this.jdbcTemplate.queryForObject(getBrandNameQuery,String.class);
    }

}
