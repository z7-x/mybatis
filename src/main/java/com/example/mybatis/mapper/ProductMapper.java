package com.example.mybatis.mapper;

import java.util.List;

import com.example.mybatis.pojo.result.PageQuery;
import com.example.mybatis.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    /**
     * 通过id获取商品
     *
     * @param id
     * @return
     */
    Product getProductById(String id);

    /**
     * 获取所有的商品
     *
     * @return
     */
    List<Product> getAllProduct();

    /**
     * 通过参数查询,筛选商品
     *
     * @param pageQuery
     * @return
     */
    List<Product> getProductList(PageQuery<Product> pageQuery);

    /**
     * 添加Product
     *
     * @param product
     */
    void addProduct(Product product);

    /**
     * 通过id删除商品
     *
     * @param id
     */
    void deleteProductById(String id);

    /**
     * 更新商品
     *
     * @param product
     */
    void updateProductById(Product product);

}
