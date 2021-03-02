package com.example.mybatis.service;

import com.example.mybatis.pojo.PageQuery;
import com.example.mybatis.pojo.Product;

import java.io.IOException;
import java.util.List;

/**
 * 商品服务接口
 *
 * @author yizl
 */
public interface IProductService {
    /**
     * 通过id获取商品详情
     *
     * @param id
     * @return
     */
    Product getProductById(String id);

    /**
     * 获取所有商品
     *
     * @return
     */
    List<Product> getAllProduct();

    /**
     * 通过参数查询
     *
     * @param pageQuery 分页查询参数
     * @return
     */
    List<Product> getProductList(PageQuery<Product> pageQuery);

    /**
     * 添加商品
     *
     * @param product
     * @throws IOException
     */
    void addProduct(Product product) throws IOException;

    /**
     * 根据id删除商品
     *
     * @param id
     * @throws IOException
     */
    void deleteProductById(String id) throws IOException;

    /**
     * 更新商品信息
     *
     * @param product
     * @throws IOException
     */
    void updateProductById(Product product) throws IOException;
}
