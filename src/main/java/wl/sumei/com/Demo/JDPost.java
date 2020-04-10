package wl.sumei.com.Demo;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.List;

public class JDPost {
    public static void main(String[] args) throws IOException {
//        1.指定网络地址
        String s = "https://item.jd.com/65333032236.html";
//        2.打开请求连接
        Document document = Jsoup.connect(s).get();
       // System.out.println(document);
        String text = document.select("div[class=tab-con] ul[class=p-parameter-list]").text();
        Elements select = document.select("ul[class=parameter2 p-parameter-list]");

        for (Element element : select) {
            System.out.println(element.text());
            String[] split = element.text().split("：");
            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i]+"      "+i);
            }
        }




    }
}
