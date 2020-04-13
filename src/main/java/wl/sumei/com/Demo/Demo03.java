package wl.sumei.com.Demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        //1.指定爬取路径
        String url ="https://www.quyuege.com/xs/38/38589/";
        Connection connect = Jsoup.connect(url);
        connect.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        connect.header("Accept-Encoding","gzip, deflate, br");
        connect.header("Accept-Language","zh-CN,zh;q=0.9");
        connect.header("Cache-Control","max-age=0");
        connect.header("Connection","keep-alive");
        connect.header("Cookie","fikker-VyZm-8GxU=HyTKb10yZ9RUVcXwxUOLD1M7XUMYV16L; fikker-VyZm-8GxU=HyTKb10yZ9RUVcXwxUOLD1M7XUMYV16L");
        connect.header("Host","www.quyuege.com");
        connect.header("If-Modified-Since","Fri, 21 Dec 2018 03:43:25 GMT");
        connect.header("Referer","https://www.quyuege.com/");
        connect.header("Sec-Fetch-Mode","navigate");
        connect.header("Sec-Fetch-Site","same-origin");
        connect.header("Sec-Fetch-User","?1");
        connect.header("Upgrade-Insecure-Requests","1");
        connect.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

        Document docs =  Jsoup.parse(new URL(url).openStream(), "GBK", url);

        Elements select = docs.select("div[class=bd] li");
        Elements a = select.select("a");

        List<String> list = new ArrayList<String>();
        for (Element element : a) {
            String href = element.attr("href");
            list.add(href);
           // System.out.println(href);
        }
        String s = list.get(4);



    }
}
