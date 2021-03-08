package com.example.mybatis.test.lucene.highlight;


import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Classname SearchTest
 * @Description TODO lucene 根据搜索域 高亮显示
 * @Date 2021/3/3 7:35 下午
 * @Author z7-x
 */
@RestController
@RequestMapping("/lucene")
public class SearchTest {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<String> search(@RequestParam String par) throws Exception {
        List<String> strings = new ArrayList<>();

        //读取指定的索引库路径
        String indexDir = "/Volumes/工作文档/spring/boot-test/springboot/z7-mybatis/lucene/indexHighlight";

        //得到读取索引文件的路径
        Directory dir = FSDirectory.open(Paths.get(indexDir));

        //通过dir得到的路径下的所有的文件
        IndexReader reader = DirectoryReader.open(dir);

        //建立索引查询器
        IndexSearcher searcher = new IndexSearcher(reader);

        //中文分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        //建立查询解析器
        /**
         * 第一个参数是要查询的字段；
         * 第二个参数是分析器Analyzer
         * */
        QueryParser parser = new QueryParser("desc", analyzer);

        //根据传进来的par查找
        Query query = parser.parse(par);

        //计算索引开始时间
        long start = System.currentTimeMillis();

        //开始查询
        /**
         * 第一个参数是通过传过来的参数来查找得到的query；
         * 第二个参数是要出查询的行数
         * */
        TopDocs topDocs = searcher.search(query, 10);

        //索引结束时间
        long end = System.currentTimeMillis();

        System.out.println("匹配" + par + ",总共花费了" + (end - start) + "毫秒,共查到" + topDocs.totalHits + "条记录。");


        //高亮显示start

        //算分
        QueryScorer scorer = new QueryScorer(query);

        //显示得分高的片段
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);

        //设置标签内部关键字的颜色
        //第一个参数：标签的前半部分；第二个参数：标签的后半部分。
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");

        //第一个参数是对查到的结果进行实例化；第二个是片段得分（显示得分高的片段，即摘要）
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);

        //设置片段
        highlighter.setTextFragmenter(fragmenter);

        //高亮显示end

        //遍历topDocs
        /**
         * ScoreDoc:是代表一个结果的相关度得分与文档编号等信息的对象。
         * scoreDocs:代表文件的数组
         * @throws Exception
         * */
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

            //获取文档
            Document document = searcher.doc(scoreDoc.doc);

            //输出全路径
            strings.add(document.get("city"));
            strings.add(document.get("desc"));

            String desc = document.get("desc");
            if (desc != null) {

                //把全部得分高的摘要给显示出来

                //第一个参数是对哪个参数进行设置；第二个是以流的方式读入
                TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));

                //获取最高的片段
                strings.add(highlighter.getBestFragment(tokenStream, desc));
            }
        }
        reader.close();
        return strings;
    }

//
//    public static void main(String[] args) {
//       /**
//         * 搜索域加权
//         */
//        Map<String, Float> boosts = new HashMap<>();
//        boosts.put("title", 1.2F);
//        boosts.put("author", 1.1F);
//        boosts.put("content", 1.0F);
//        Set<Map.Entry<String, Float>> entries = boosts.entrySet();
//        entries.forEach(maps -> {
//            System.out.println(maps);
//        });
//
//        /**
//         * 多条件之间的关系
//         */
//        BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,
//                BooleanClause.Occur.SHOULD,
//                BooleanClause.Occur.SHOULD};
//        Query query= MultiFieldQueryParser.parse(title, new String[]{"title","author","content"},flags, new IKAnalyzer());
//    }
}
