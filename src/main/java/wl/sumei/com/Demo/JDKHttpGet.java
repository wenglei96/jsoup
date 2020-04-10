package wl.sumei.com.Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JDKHttpGet {
    public static void main(String[] args) throws IOException {
        //1.指定url地址
        String  z = "http://www.baidu.com";
        //2.发起一个请求
        URL url1 = new URL(z);
        URLConnection connection = url1.openConnection();//打开请求
        //3.发送数据

        //4.得到一个响应流数据
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;
        while((line = reader.readLine())!= null ){
            System.out.println(line);
        }

    }
}
