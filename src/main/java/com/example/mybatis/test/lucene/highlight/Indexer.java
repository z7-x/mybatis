package com.example.mybatis.test.lucene.highlight;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * @Classname Indexer
 * @Description TODO 创建 高亮显示索引库
 * @Date 2021/3/3 6:59 下午
 * @Author z7-x
 */
public class Indexer {

    private String[] ids = {"1", "2", "3"};
    private String citys[] = {"青岛", "南京", "上海"};
    private String descs[] = {
            "青岛是一个漂亮的城市。",
            "南京是一个文化的城市。",
            "上海是一个繁华的城市。"
    };

    private Directory dir;

    /**
     * 实例化indexerWriter
     *
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {

        //中文分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        IndexWriter writer = new IndexWriter(dir, iwc);

        return writer;
    }

    /**
     * 获取indexDir
     *
     * @param indexDir
     * @throws Exception
     */
    private void index(String indexDir) throws Exception {

        dir = FSDirectory.open(Paths.get(indexDir));

        IndexWriter writer = getWriter();

        for (int i = 0; i < ids.length; i++) {

            Document doc = new Document();

            doc.add(new StringField("id", ids[i], Field.Store.YES));
            doc.add(new StringField("city", citys[i], Field.Store.YES));
            doc.add(new TextField("desc", descs[i], Field.Store.YES));

            writer.addDocument(doc);
        }
        writer.close();
    }


    public static void main(String[] args) throws Exception {
        new Indexer().index("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexHighlight");
        System.out.println("Success Indexer");
    }
}
