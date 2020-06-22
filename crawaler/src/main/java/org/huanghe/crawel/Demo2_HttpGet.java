package org.huanghe.crawel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author River
 * @date 2020/6/23 6:31
 * @description
 */
public class Demo2_HttpGet {

    public static void main(String[] args) throws Exception{
        //1.创建对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        uriBuilder.setParameter("keys","java");

        //2.访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.解析响应
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }
        //5.关闭response
        response.close();
        httpClient.close();
    }
}
