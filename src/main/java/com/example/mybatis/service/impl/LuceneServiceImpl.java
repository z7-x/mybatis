package com.example.mybatis.service.impl;


import java.io.IOException;
import java.util.List;

import com.example.mybatis.dao.ILuceneDao;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.pojo.result.PageQuery;
import com.example.mybatis.pojo.Product;
import com.example.mybatis.service.ILuceneService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuceneServiceImpl implements ILuceneService {

	@Autowired
	private ILuceneDao luceneDao;

	@Autowired
	private ProductMapper mapper;

	@Override
	public void synProductCreatIndex() throws IOException {
		// 获取所有的productList
		List<Product> allProduct = mapper.getAllProduct();
		// 再插入productList
		luceneDao.createProductIndex(allProduct);
	}

	@Override
	public PageQuery<Product> searchProduct(PageQuery<Product> pageQuery) throws IOException, ParseException {
		return luceneDao.searchProduct(pageQuery);
	}

	@Override
	public List<Product> searchProduct(String pram) throws IOException, ParseException {
		return luceneDao.searchProduct(pram);
	}
}
