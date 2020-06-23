package org.huanghe.crawel.jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * @author River
 * @date 2020/6/23 7:02
 * @description
 */
public class Demo2_StringDocument {
    public static void main(String[] args) throws Exception {
        //1.实用工具类获取文件的字符串
        String content = FileUtils.readFileToString(new File("f:\\......"), "utf8");
        //2.解析
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }
}
