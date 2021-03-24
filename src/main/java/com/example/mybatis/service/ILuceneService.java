package com.example.mybatis.service;

import java.io.IOException;
import java.util.List;

import com.example.mybatis.pojo.result.PageQuery;
import com.example.mybatis.pojo.Product;
import org.apache.lucene.queryparser.classic.ParseException;


public interface ILuceneService {
	/**
	 * 启动后将同步Product表,并创建index
	 * @throws IOException 
	 */
	 void synProductCreatIndex() throws IOException;

	/**
	 * 分页
	 * @param pageQuery 分页条件
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	 PageQuery<Product> searchProduct(PageQuery<Product> pageQuery) throws IOException, ParseException;


	List<Product> searchProduct(String pram) throws IOException, ParseException;
}
