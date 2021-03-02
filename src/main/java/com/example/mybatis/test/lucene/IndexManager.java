package com.example.mybatis.test.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;


/**
 * @Classname IndexManager
 * @Description TODO 索引库维护「添加文档、删除索引库、更新索引库」
 * @Date 2021/3/1 2:15 下午
 * @Author z7-x
 */
public class IndexManager {

    private IndexWriter indexWriter;

    @Before
    public void init() throws Exception {
        //1、创建一个IndexWriter对象，需要用IKAnalyzer
        indexWriter =
                new IndexWriter(FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath()),
                        new IndexWriterConfig(new IKAnalyzer()));
    }

    /**
     * @Description: 方法 addDocument 的功能描述：TODD 添加文档
     * @Date 2021/3/1 2:17 下午
     */
    @Test
    public void addDocument() throws Exception {
        //1、创建一个IndexWriter对象，需要用IKAnalyzer
        IndexWriter indexWriter =
                new IndexWriter(FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath()),
                        new IndexWriterConfig(new IKAnalyzer()));
        //2、创建一个document对象
        Document document = new Document();
        //3、向document对象中添加域
        document.add(new TextField("name", "新添加的文件", Field.Store.YES));
        document.add(new TextField("content", "新添加的文件内容", Field.Store.YES));
        document.add(new StoredField("path", "新添加的文件路径：/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/"));
        //4、把文档写入索引库
        indexWriter.addDocument(document);
        //5、关闭索引库
        indexWriter.close();
    }

    /**
     * @Description: 方法 deleteAllDocument 的功能描述：TODD 删除全部文档
     * @Date 2021/3/1 2:43下午
     */
    @Test
    public void deleteAllDocument() throws Exception {
        indexWriter.deleteAll();
        indexWriter.close();
    }

    /**
     * @Description: 方法 deleteDocumentByQuery 的功能描述：TODD 删除查询的文档
     * @Date 2021/3/1 2:41下午
     */
    @Test
    public void deleteDocumentByQuery() throws Exception {
        indexWriter.deleteDocuments(new Term("content", "level"));
        indexWriter.close();
    }

    /**
     * @Description: 方法 updateDocument 的功能描述：TODD 更新文档  先删除文档 再重新新添加一个文档
     * @Date 2021/3/1 2:51下午
     */
    @Test
    public void updateDocument() throws Exception {
        //1、创建一个文档对象
        Document document = new Document();
        //2、向文档中添加域
        document.add(new TextField("name", "更新之后的文档", Field.Store.YES));
        document.add(new TextField("name1", "更新之后的文档1", Field.Store.YES));
        document.add(new TextField("name2", "更新之后的文档2", Field.Store.YES));
        //3、更新 将name域中包含spring的文档删除，后添加document文档
        indexWriter.updateDocument(new Term("name", "spring"), document);
        indexWriter.close();

    }


}
