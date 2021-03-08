package com.example.mybatis.test.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Classname LuceneTest
 * @Description TODO 创建索引库 查询索引库 标准分词器 ik分词器
 * @Date 2021/2/26 10:02 上午
 * @Author z7-x
 */
public class LuceneTest {

    /**
     * 创建索引 非结构化数据
     *
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        //1、创建一个Directory对象，指定索引库的位置 获取该目录下数据源
        //把索引库保存在内存中 Directory ramDirectory = new RAMDirectory();
        Directory directory = FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath());
        //2、创建一个读取IndexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig());
        //3、读取磁盘中的文件，对应的每个文件创建一个对象
        File file = new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/dataResource");
        //4、向文档对象中添加域
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            //取文件名
            String fileName = f.getName();
            //取文件路径
            String filePath = f.getPath();
            //读取文件内容
            String fileContent = null;
            try {
                fileContent = FileUtils.readFileToString(f, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //读取文件大小
            long fileSize = FileUtils.sizeOf(f);
            //创建Filed对象:第一个参数 域名称 第二个参数：域的内容  第三个参数：是否存储到磁盘
            Field name = new TextField("name", fileName, Field.Store.YES);
            Field path = new TextField("path", filePath, Field.Store.YES);
            Field content = new TextField("content", fileContent, Field.Store.YES);
            Field size = new TextField("size", String.valueOf(fileSize), Field.Store.YES);

            //创建文档对象
            Document document = new Document();
            //向文档中添加域
            document.add(name);
            document.add(path);
            document.add(content);
            document.add(size);

            //5、把文档写入索引库
            try {
                indexWriter.addDocument(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //6、关闭indexWriter对象
        indexWriter.close();
    }

    /**
     * 读取索引 非结构化数据
     *
     * @throws Exception
     */
    @Test
    public void searchIndex() throws Exception {
        //1、创建一个Directory对象，指定索引库的位置
        Directory directory = FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath());
        //2、创建一个读取IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //3、创建一个IndexSearcher对象，构造方法中的参数indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //4、创建一个Query对象TermQuery:Term第一个参数 关键词所在域  Term第二个参数:该域中所要查询的关键词
        Query query = new TermQuery(new Term("name", "spring"));
        //5、执行查询，得到一个TopDocs对象:第一个参数 查询对象 第二个参数:返回查询结果所需的的记录数
        TopDocs topDocs = indexSearcher.search(query, 100);
        //6、取查询结果的记录数
        long totalHits = topDocs.totalHits;
        System.out.println("查询返回的命中文件记录数：" + totalHits);
        //7、取文档列表
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //8、打印文档中的内容
        Arrays.stream(scoreDocs).forEach(scoreDoc -> {
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
        //9、关闭IndexReader对象
        indexReader.close();
    }


    /**
     * 标准分词器
     *
     * @throws Exception
     */
    @Test
    public void tokenStream() throws Exception {
        //1、创建一个Analyzer对象，StandardAnalyzer对象
        Analyzer analyzer = new StandardAnalyzer();
        //2、使用分词器对象的tokenStream的方法获得TokenStream对象
        TokenStream tokenStream = analyzer.tokenStream("name", "Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎（英文与德文两种西方语言）");
        //3、向tokenStream对象中设置一个引用，相当于数的一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4、调用TokenStream对象的rest方法
        tokenStream.reset();
        //5、使用while循环遍历TokenStream对象
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        //6、关闭TokenStream对象
        tokenStream.close();
    }

    /**
     * IK分词器
     *
     * @throws Exception
     */
    @Test
    public void ikAnalyzer() throws Exception {
        //1、创建一个Analyzer对象，StandardAnalyzer对象
        Analyzer analyzer = new IKAnalyzer();
        //2、使用分词器对象的tokenStream的方法获得TokenStream对象
        TokenStream tokenStream = analyzer.tokenStream("", "办公用品");
        //3、向tokenStream对象中设置一个引用，相当于数的一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4、调用TokenStream对象的rest方法
        tokenStream.reset();
        //5、使用while循环遍历TokenStream对象
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        //6、关闭TokenStream对象
        tokenStream.close();
    }

    /**
     * 使用IKAnalyzer创建索引库
     *
     * @throws Exception
     */
    @Test
    public void ikCreateIndex() throws Exception {
        //1、创建一个Directory对象，指定索引库的位置 获取该目录下数据源
        //把索引库保存在内存中 Directory ramDirectory = new RAMDirectory();
        Directory directory = FSDirectory.open(new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexResource").toPath());
        //2、创建一个读取IndexWriter对象 使用ik分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //3、读取磁盘中的文件，对应的每个文件创建一个对象
        File file = new File("/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/dataResource");
        //4、向文档对象中添加域
        File[] files = file.listFiles();
        for (File f : files) {//取文件名
            String fileName = f.getName();
            //取文件路径
            String filePath = f.getPath();
            //读取文件内容
            String fileContent = null;
            try {
                fileContent = FileUtils.readFileToString(f, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //读取文件大小
            long fileSize = FileUtils.sizeOf(f);
            //创建Filed对象:第一个参数 域名称 第二个参数：域的内容  第三个参数：是否存储到磁盘
            Field name = new TextField("name", fileName, Field.Store.YES);
//            Field path = new TextField("path", filePath, Field.Store.YES);
            Field path = new StoredField("path", filePath);//只存储，不分词
            Field content = new TextField("content", fileContent, Field.Store.YES);
//            Field size = new TextField("size", String.valueOf(fileSize), Field.Store.YES);
            Field size = new LongPoint("size", fileSize);//运算
            Field storedSize = new StoredField("size", fileSize);//存储
            //创建文档对象
            Document document = new Document();
            //向文档中添加域
            document.add(name);
            document.add(path);
            document.add(content);
            document.add(size);
            document.add(storedSize);
//            document.add(size);

            //5、把文档写入索引库
            try {
                indexWriter.addDocument(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //6、关闭indexWriter对象
        indexWriter.close();

    }
}
