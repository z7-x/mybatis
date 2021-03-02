package com.example.mybatis.dao;

import java.io.IOException;
import java.util.List;

import com.example.mybatis.pojo.PageQuery;
import com.example.mybatis.pojo.Product;
import org.apache.lucene.queryparser.classic.ParseException;

public interface ILuceneDao {
    /**
     * 创建索引
     *
     * @param productList
     * @throws IOException
     */
    void createProductIndex(List<Product> productList) throws IOException;

    /**
     * 查询索引
     *
     * @param pageQuery
     * @return
     * @throws IOException
     * @throws ParseException
     */
    PageQuery<Product> searchProduct(PageQuery<Product> pageQuery) throws IOException, ParseException;

    /**
     * 添加一个新索引
     *
     * @param product
     * @throws IOException
     */
    void addProductIndex(Product product) throws IOException;

    /**
     * 通过id删除商品索引
     *
     * @param id
     * @throws IOException
     */
    void deleteProductIndexById(String id) throws IOException;


}
