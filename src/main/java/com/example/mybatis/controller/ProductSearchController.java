package com.example.mybatis.controller;



import java.io.IOException;
import java.util.List;

import com.example.mybatis.pojo.PageQuery;
import com.example.mybatis.pojo.Product;
import com.example.mybatis.pojo.ResultBean;
import com.example.mybatis.service.ILuceneService;
import com.example.mybatis.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product/search")
@Api(description = "查询商品")
public class ProductSearchController {
	
	@Autowired
	private ILuceneService service;

	@ApiOperation(value = "查询商品", notes = "查询商品")
	@RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
	private ResultBean<PageQuery<Product>> searchProduct(@RequestBody PageQuery<Product> pageQuery) throws IOException, ParseException {
		PageQuery<Product> pageResult= service.searchProduct(pageQuery);
		return ResultUtil.success(pageResult);
	}

	@ApiOperation(value = "查询商品", notes = "查询商品")
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	private ResultBean<List<Product>> param(@RequestParam String param) throws IOException, ParseException {
		List<Product> products = service.searchProduct(param);
		return ResultUtil.success(products);
	}
	
}
