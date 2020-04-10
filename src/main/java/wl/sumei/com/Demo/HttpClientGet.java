package wl.sumei.com.Demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpClientGet {
    public static void main(String[] args) throws IOException {

//        1.导入jar包
//        2.指定请求地址
        String s ="http://www.baidu.com";
//        3.打开一个连接
        HttpGet httpGet = new HttpGet(s);
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        4.发送数据
//        5.执行并获取数据
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        HttpEntity entity = execute.getEntity();
//        6.打印数据
        String s1 = EntityUtils.toString(entity, Charset.forName("utf-8"));
        System.out.println(s1);

    }
}
