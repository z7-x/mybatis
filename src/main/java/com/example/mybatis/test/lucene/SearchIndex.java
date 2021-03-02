package com.example.mybatis.test.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Classname SearchIndex
 * @Description TODO 查询索引
 * @Date 2021/3/1 3:25 下午
 * @Author z7-x
 */
public class SearchIndex {

    private IndexReader indexReader;
    private IndexSearcher indexSearcher;

    @Before
    public void init() throws Exception {
        Directory directory = FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath());
        indexReader = DirectoryReader.open(directory);
        indexSearcher = new IndexSearcher(indexReader);
    }

    /**
     * 范围查询
     *
     * @throws Exception
     */
    @Test
    public void RangQuery() throws Exception {
        //创建一个查询对象
        Query query = LongPoint.newRangeQuery("size", 0L, 1000L);
        printResult(query);
    }

    /**
     * s 查询
     *
     * @throws Exception
     */
    @Test
    public void QueryParser() throws Exception {
        //创建QueryParser 参数一：默认搜索域 参数二：分词器对象
        QueryParser queryParser = new QueryParser("content", new IKAnalyzer());
        //创建一个Query对象
        Query query = queryParser.parse("level");
        printResult(query);
    }


    public void printResult(Query query) throws Exception {
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("总记录数" + topDocs.totalHits);
        Arrays.stream(topDocs.scoreDocs).forEach(scoreDoc -> {
            //取文档id
            int doc = scoreDoc.doc;
            //根据文档id取文档对象
            try {
                Document document = indexSearcher.doc(doc);
                System.out.println(document.get("name"));
                System.out.println(document.get("path"));
                System.out.println(document.get("size"));
                System.out.println(document.get("content"));
                System.out.println("-----------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        indexReader.close();
    }
}
