package org.huanghe.crawel.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

/**
 * @author River
 * @date 2020/6/23 7:29
 * @description
 */
public class Demo4_Selector {

    public static void main(String[] args) throws Exception {

        //1.解析文件，获取Document对象
        Document doc = Jsoup.parse(new File(""), "utf8");
        Elements elements = doc.select("span");
        for (Element element : elements) {
            System.out.println(element.text());
        }

        Element element1 = doc.select("#city_id").first();
        System.out.println(element1.text());

        Element el = doc.select(".class_a").first();

    }

}
