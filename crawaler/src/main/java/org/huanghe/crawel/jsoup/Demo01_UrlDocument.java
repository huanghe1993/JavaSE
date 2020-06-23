package org.huanghe.crawel.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author River
 * @date 2020/6/23 6:58
 * @description
 */
public class Demo01_UrlDocument {

    public static void main(String[] args) throws Exception {
        //1.解析Url地址,第二个参数是访问超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        //2.使用标签选择器，获取title
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }
}
