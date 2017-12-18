package com.jaso.base.lucene;

import com.hankcs.lucene.HanLPAnalyzer;
import com.jaso.base.util.StringUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class Search {

    //搜索类
    public List<String> search(String keyword){

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
            Analyzer analyzer = new HanLPAnalyzer();
//            Analyzer analyzer = new StandardAnalyzer();

            //创建查询解析器
            // 参数一: 要查询的范围(文件名,文件内容)
            // 参数二: 分词器
            QueryParser queryParser = new QueryParser("menu",analyzer);

            //对搜索关键字进行解析
            Query query = queryParser.parse(keyword);

            //使用indexsearcher进行查询,得到结果集
            TopDocs hits = indexSearcher.search(query, 10);

            //
            System.out.println(" ↓ 共搜索到结果数: "+hits.totalHits);

            //获取结果集
            ScoreDoc[] scoreDocs = hits.scoreDocs;

            List<String> menuName = new ArrayList<String>();
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                menuName.add(document.get("menu"));
                System.out.println(document.get("menu"));
            }
            return menuName;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
