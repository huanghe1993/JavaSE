package org.huanghe.crawel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author River
 * @date 2020/6/23 6:33
 * @description
 */
public class Demo03_HttpPost {

    public static void main(String[] args) throws Exception{
        //1.创建对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.访问地址
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");

        //3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

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
