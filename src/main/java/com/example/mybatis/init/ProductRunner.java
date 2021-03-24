package com.example.mybatis.init;

import com.example.mybatis.service.ILuceneService;
import com.example.mybatis.test.lucene.LuceneTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动后,立即执行
 * ToDo 创建结构化索引库
 * @author  z7-x
 *
 */
@Component
@Order(value = 1)
public class ProductRunner implements ApplicationRunner {

	@Autowired
	private ILuceneService iLuceneService;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		/**
		 * 启动后将同步Product表,并创建index
		 */
		System.out.println("创建索引库...");
		iLuceneService.synProductCreatIndex();
	}
}
