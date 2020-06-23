package org.huanghe.crawel.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * @author River
 * @date 2020/6/23 7:39
 * @description
 */
public class Demo5_Selector {

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.parse(new File(""), "utf8");
        Element element = doc.select("h3#city_b").first();
        Element element2 = doc.select("li.class_a").first();
        // span中有abc属性的
        Element element3 = doc.select("span[abc]").first();
        // span标签包含abc属性 span[abc] 然后找到class有s_name的 span[abc].s_name
        Element element4 = doc.select("span[abc].s_name").first();
        System.out.println(element.text());

    }
}
