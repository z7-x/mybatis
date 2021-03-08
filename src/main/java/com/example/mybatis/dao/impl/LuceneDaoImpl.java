package com.example.mybatis.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.mybatis.dao.ILuceneDao;
import com.example.mybatis.pojo.PageInfo;
import com.example.mybatis.pojo.PageQuery;
import com.example.mybatis.pojo.Product;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FloatDocValuesField;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

@Repository(value = "luceneDao")
public class LuceneDaoImpl implements ILuceneDao {

    @Autowired(required = false)
    private IndexWriter indexWriter;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;

    @Override
    public void createProductIndex(List<Product> productList) throws IOException {
        List<Document> docs = new ArrayList<Document>();
        for (Product p : productList) {
            Document doc = new Document();
            doc.add(new StringField("id", p.getId() + "", Field.Store.YES));

            doc.add(new TextField("name", p.getName(), Field.Store.YES));
            doc.add(new StringField("category", p.getCategory(), Field.Store.YES));

            // 保存price
            float price = p.getPrice();
            // 建立倒排索引
            doc.add(new FloatPoint("price", price));
            // 正排索引用于排序、聚合
            doc.add(new FloatDocValuesField("price", price));
            // 存储到索引库
            doc.add(new StoredField("price", price));

            doc.add(new TextField("place", p.getPlace(), Field.Store.YES));
            doc.add(new StringField("code", p.getCode(), Field.Store.YES));
            doc.add(new StringField("describe", p.getCode(), Field.Store.YES));
            docs.add(doc);
        }
        indexWriter.addDocuments(docs);
        indexWriter.commit();
    }

    @Override
    public PageQuery<Product> searchProduct(PageQuery<Product> pageQuery) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        Product params = pageQuery.getParams();
        Map<String, String> queryParam = pageQuery.getQueryParam();
        Builder builder = new Builder();
        Sort sort = new Sort();
        // 排序规则
        com.example.mybatis.pojo.Sort sort1 = pageQuery.getSort();
        if (sort1 != null && sort1.getOrder() != null) {
            if ("ASC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, false));
            } else if ("DESC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, true));
            }
        }

        // 模糊匹配,匹配词
        if (queryParam != null) {
            String keyStr = queryParam.get("searchKeyStr");
            if (keyStr != null) {
                // 输入空格,不进行模糊查询
                if (!"".equals(keyStr.replaceAll(" ", ""))) {
                    builder.add(new QueryParser("name", analyzer).parse(keyStr), Occur.MUST);
                }
            }
        }

        // 精确查询
        if (params.getCategory() != null) {
            builder.add(new TermQuery(new Term("category", params.getCategory())), Occur.MUST);
        }
        if (queryParam != null) {
            if (queryParam.get("lowerPrice") != null && queryParam.get("upperPrice") != null) {
                // 价格范围查询
                builder.add(FloatPoint.newRangeQuery("price", Float.parseFloat(queryParam.get("lowerPrice")),
                        Float.parseFloat(queryParam.get("upperPrice"))), Occur.MUST);
            }
        }
        PageInfo pageInfo = pageQuery.getPageInfo();
        TopDocs topDocs = indexSearcher.search(builder.build(), pageInfo.getPageNum() * pageInfo.getPageSize(), sort);

        pageInfo.setTotal(topDocs.totalHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<Product> pList = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            System.out.println(doc.toString());
            Product product = new Product();
            product.setId(Integer.parseInt(doc.get("id")));
            product.setName(doc.get("name"));
            product.setCategory(doc.get("category"));
            product.setPlace(doc.get("place"));
            product.setPrice(Float.parseFloat(doc.get("price")));
            product.setCode(doc.get("code"));
            pList.add(product);
        }
        pageQuery.setResults(pList);
        return pageQuery;
    }

    @Override
    public List<Product> searchProduct(String pram) throws IOException, ParseException {
        List<Product> products = new ArrayList<>();
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        QueryParser parser = new QueryParser("describe", new IKAnalyzer());
        Query query = parser.parse(pram);
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc doc : scoreDocs) {
            int id = doc.doc;
            Document document = indexSearcher.doc(id);
            Product product = new Product();
            product.setId(Integer.parseInt(document.get("id")));
            product.setCode(document.get("code"));
            product.setName(document.get("name"));
            product.setPlace(document.get("place"));
            product.setPrice(Float.parseFloat(document.get("price")));
            product.setCategory(document.get("category"));
            product.setDescribe(document.get("describe"));
            products.add(product);
        }
        return products;
    }

    @Override
    public void addProductIndex(Product product) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("id", product.getId() + "", Field.Store.YES));

        doc.add(new TextField("name", product.getName(), Field.Store.YES));
        doc.add(new StringField("category", product.getCategory(), Field.Store.YES));
        // 保存price,
        float price = product.getPrice();
        // 建立倒排索引
        doc.add(new FloatPoint("price", price));
        // 正排索引用于排序、聚合
        doc.add(new FloatDocValuesField("price", price));
        // 存储到索引库
        doc.add(new StoredField("price", price));

        doc.add(new TextField("place", product.getPlace(), Field.Store.YES));
        doc.add(new StringField("code", product.getCode(), Field.Store.YES));
        indexWriter.addDocument(doc);
        indexWriter.commit();
    }

    @Override
    public void deleteProductIndexById(String id) throws IOException {
        indexWriter.deleteDocuments(new Term("id", id));
        indexWriter.commit();
    }


}
