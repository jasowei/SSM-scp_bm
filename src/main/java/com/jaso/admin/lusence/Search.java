package com.jaso.admin.lusence;

import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class Search {

    public static final String REGEX_MOBILE = "(^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$)||(\\d{3}-\\d{8})||(\\d{4}-\\d{7})";
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    //搜索类
    public List<String> search(String keyword) {

        String classPath = this.getClass().getResource("/").getPath() + "/index";
        try {
            //读取索引文件的内容
            Directory directory =
                    FSDirectory.open(FileSystems.getDefault().getPath(classPath));

            //创建索引文件的读对象
            DirectoryReader reader = DirectoryReader.open(directory);

            //创建索引搜索对象
            IndexSearcher indexSearcher = new IndexSearcher(reader);

            //创建标准分词器
//            Analyzer analyzer = new HanLPAnalyzer();
            Analyzer analyzer = new StandardAnalyzer();

            //创建查询解析器
            // 参数一: 要查询的范围(文件名,文件内容)
            // 参数二: 分词器
            QueryParser queryParser;
            if (keyword.matches(REGEX_MOBILE)) {
                queryParser = new QueryParser("telephone", analyzer);
            } else if (keyword.matches(REGEX_EMAIL)) {
                queryParser = new QueryParser("email", analyzer);
            }else {
                queryParser = new QueryParser("admin", analyzer);
            }

            //对搜索关键字进行解析
            Query query = queryParser.parse(keyword);

            //使用indexsearcher进行查询,得到结果集
            TopDocs hits = indexSearcher.search(query, 10);

            //
            System.out.println(" ↓ 共搜索到结果数: " + hits.totalHits);

            //获取结果集
            ScoreDoc[] scoreDocs = hits.scoreDocs;

            List<String> adminName = new ArrayList<String>();
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                if (keyword.matches(REGEX_MOBILE)) {
                    adminName.add(document.get("telephone"));
                } else if (keyword.matches(REGEX_EMAIL)) {
                    adminName.add(document.get("email"));
                }else {
                    adminName.add(document.get("admin"));
                }
            }
            return adminName;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
