package wl.sumei.com.Demo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JDKHttpPost {
    public static void main(String[] args) throws IOException {
//        1.指定url路径
            String s = "https://www.baidu.com";
//            2.客户端发起请求
        URL url = new URL(s);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //
//        connection.addRequestProperty("");
        //设置请求方式
        connection.setRequestMethod("POST");

        //打开doput
        connection.setDoOutput(true);

//        3.发送数据给服务器端
        OutputStream stream = connection.getOutputStream();
        stream.write("username=wenglei&&password=zhangxiulian".getBytes());
        stream.flush();
        stream.close();

//        4.得到一个响应流数据,输入流，HTML二进制
        InputStream inputStream = connection.getInputStream();
//        5.打印结果，一行一行显示
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;

        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }
}
