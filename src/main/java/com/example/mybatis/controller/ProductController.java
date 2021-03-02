package com.example.mybatis.controller;

import java.io.IOException;
import java.util.List;

import com.example.mybatis.pojo.PageInfo;
import com.example.mybatis.pojo.PageQuery;
import com.example.mybatis.pojo.Product;
import com.example.mybatis.pojo.ResultBean;
import com.example.mybatis.service.IProductService;
import com.example.mybatis.utils.ResultUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/product")
@Api(description = "操作商品")
public class ProductController {

    @Autowired
    private IProductService service;

    @ApiOperation(value = "hello", notes = "hello")
    @RequestMapping(value = "/helloProduct", method = RequestMethod.GET)
    public String helloProduct() {
        return "Hello Product !";
    }

    @ApiOperation(value = "查询商品列表", notes = "查询商品列表")
    @RequestMapping(value = "/getProductList", method = RequestMethod.GET)
    public ResultBean<PageQuery<Product>> getProductList(@RequestBody PageQuery<Product> pageQuery) {
        PageInfo pageInfo = pageQuery.getPageInfo();
        Page<Product> page = PageHelper.startPage(pageInfo);
        //也可以使用PageHelper的排序
        //PageHelper.orderBy("id desc");
        List<Product> pList = service.getProductList(pageQuery);
        pageInfo.setTotal(page.getTotal());
        pageQuery.setResults(pList);

        return ResultUtil.success(pageQuery);
    }

    @ApiOperation(value = "添加商品", notes = "添加商品")
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ResultBean<Product> addProduct(@RequestBody Product product) throws IOException {
        service.addProduct(product);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @RequestMapping(value = "/deleteProductById", method = RequestMethod.GET)
    public ResultBean<Product> deleteProductById(String id) throws IOException {
        service.deleteProductById(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "更新商品", notes = "更新商品")
    @RequestMapping(value = "/updateProductById", method = RequestMethod.GET)
    public ResultBean<Product> updateProductById(@RequestBody Product product) throws IOException {
        service.updateProductById(product);
        return ResultUtil.success();
    }

}
