package wl.sumei.com.Demo;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class HttpClientPost {
    public static void main(String[] args) throws IOException {

//        1.指定请求路径
        String s = "http://www.baidu.com";
//        2.打开连接
        HttpPost post = new HttpPost(s);
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        3.发送数据
        ArrayList<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("username","wenglei"));
        valuePairs.add(new BasicNameValuePair("password","zhangxiulian"));

        post.setEntity(new UrlEncodedFormEntity(valuePairs));

//        4.执行并获取数据
        CloseableHttpResponse execute = httpClient.execute(post);
        HttpEntity entity = execute.getEntity();


//        5.打印数据
        String s1 = EntityUtils.toString(entity, Charset.forName("utf-8"));
        System.out.println(s1);
    }
}
