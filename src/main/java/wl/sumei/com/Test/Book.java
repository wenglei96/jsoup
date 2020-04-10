package wl.sumei.com.Test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Book {
    public static void main(String[] args) throws IOException {
//        1.指定连接网址
        String s = "https://read.qidian.com/chapter/A1a9C_srHMCrNX7uI21afA2/QVHmwHzFPNNp4rPq4Fd4KQ2";
//      2.打开连接，获取HTML数据
        Connection connect = Jsoup.connect(s);
        Document document = connect.get();
//      3.获取电子书首页目录信息
        String text = document.select("div[class=book-cover-wrap]").text();

        System.out.println(text);

        /*创建字符串输出流*/
        /*true的意思是在文件内容追加，false是覆盖原内容*/
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("D://电子书.txt", true));
        outputStream.write(text.getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.flush();
//
        getText(s, outputStream);

        outputStream.close();


    }

    public static void getText(String s, BufferedOutputStream outputStream) throws IOException {

        String url = s;
        while (url != null) {

//            获取链接地址，获取数据
            Document document = Jsoup.connect(url).get();
//            获取章节目录
            String text = document.select("div[class=main-text-wrap] h3[class=j_chapterName]").text();
            System.out.println(text);

            outputStream.write(text.getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.flush();


//            获取文段
            Elements select = document.select("div[class=read-content j_readContent] p");
            for (Element element : select) {
                System.out.println(element.text());

                outputStream.write(element.text().getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.flush();
            }


            Elements select1 = document.select("a[id=j_chapterNext]");
            String href = select1.attr("href");
            //System.out.println(href);
            String[] split = href.split("/");
            // for (String s1 : split) {
            //    System.out.println(s1);
            //}


            String ss = "https://" + split[2] + "/" + split[3];
            // System.out.println(ss);
            if (ss.equals("https://read.qidian.com/chapter")) {
                url = "https:" + href;
            } else {
                url = null;
            }

        }
    }

}
