package wl.sumei.com.Jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {

        String url ="https://www.xiaohongshu.com/explore";
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements select = document.select("div[class=header red-header light-theme] a[class=active business-link creative-link active]");

        System.out.println(select);


    }
}
