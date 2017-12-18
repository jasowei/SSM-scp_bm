package com.jaso.base.lucene;

import com.hankcs.lucene.HanLPAnalyzer;
import com.jaso.base.bean.Menu;
import com.jaso.base.mapper.MenuMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

/**
 * 索引
 */
public class Index {



    public void index(MenuMapper menuMapper){
        IndexWriter indexWriter = null;
        String classPath = this.getClass().getResource("/").getPath() + "/index";
        File indexDir = new File(classPath);
        if (!indexDir.exists() || !indexDir.isDirectory()){
            indexDir.mkdirs();
        }
        try {
            //创建索引的目录对象
            FSDirectory directory =
                    FSDirectory.open(FileSystems.getDefault().getPath(classPath));

            //创建分词器
//            Analyzer analyzer = new StandardAnalyzer();
            Analyzer analyzer = new HanLPAnalyzer();

            //创建写入索引对象的配置
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

            //创建写入索引对象(需要传入索引的保存路径和分词器配置)
            indexWriter = new IndexWriter(directory,indexWriterConfig);

            indexWriter.deleteAll();
/**
 * 创建索引
 */


            List<Menu> menuList=menuMapper.findAll();

            for (Menu menu : menuList) {
                Document doc = new Document();

                    doc.add(new Field("menu", menu.getMenu_name(), TextField.TYPE_STORED));


                //写入
                indexWriter.addDocument(doc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

                try {
                    if (indexWriter != null){
                    indexWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

}
