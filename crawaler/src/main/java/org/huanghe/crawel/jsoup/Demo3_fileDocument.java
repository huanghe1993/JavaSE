package org.huanghe.crawel.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * @author River
 * @date 2020/6/23 7:04
 * @description
 */
public class Demo3_fileDocument {

    public static void main (String[] args) throws Exception {
        Document doc = Jsoup.parse(new File(""), "utf8");
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }
}
