package com.jaso.base.util;

import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongren1 on 2017/11/23.
 */
public class AnalyzerUtils {
    public static List<String> getWords(String str, Analyzer analyzer){
        List<String> result = new ArrayList<String>();
        TokenStream stream = null;
        try {
            stream = analyzer.tokenStream("menu", new StringReader(str));
            CharTermAttribute attr = stream.addAttribute(CharTermAttribute.class);
            stream.reset();
            while(stream.incrementToken()){
                result.add(attr.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
