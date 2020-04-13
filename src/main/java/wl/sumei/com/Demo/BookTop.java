package wl.sumei.com.Demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookTop {
    public static void main(String[] args) throws IOException {
        //1.指定爬取路径
        String url ="https://www.quyuege.com/allvisit/1/";

        URLdl(url);
        //模拟浏览器登录

        //2.获取二进制 ，html文件,解决中文乱码
        Document doc =  Jsoup.parse(new URL(url).openStream(), "GBK", url);


        //3.获取书地址
        List<String> books = Books(doc);
        for (String book : books) {
            System.out.println(book);
            // 4.获取第一章地址
            String s = BookSM(book);
            System.out.println(book+s);
            //5.获取文章明细
            BookMX(book+s);
        }

    }



    private static void URLdl(String url) {
        //模拟浏览器登录
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
    }


    //获取排名小说
    private static List<String> Books(Document doc) {
        Elements select = doc.select("div[class=topba-box]  ul[class=topba-list]");
        Elements li = select.select("li a");
        // System.out.println(li);

        List<String> s = new ArrayList<String>();
        //地址值
        for (Element element : li) {
            //获取数地址
            String href = element.attr("href");

            String[] split = href.split("/");
            int length = split.length;

            if(length == 4){
                //获取书名
                String text = element.text();
              //  System.out.println(href+"  "+length+" "+text);
                s.add("https://www.quyuege.com"+href);
            }
        }
        return  s;
    }


    //获取第一章地址
    private static String BookSM(String book) throws IOException {
        //1.指定爬取路径

        Connection connect = Jsoup.connect(book);
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

        Document docs =  Jsoup.parse(new URL(book).openStream(), "GBK", book);

        Elements select = docs.select("div[class=bd] li");
        Elements a = select.select("a");

        List<String> list = new ArrayList<String>();
        for (Element element : a) {
            String href = element.attr("href");
            list.add(href);
            // System.out.println(href);
        }
        String s = list.get(4);

        return s;
    }



    private static void BookMX(String url) throws IOException {
        while (url != null) {
            //模拟浏览器登录
            Connection connect = Jsoup.connect(url);
            connect.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            connect.header("Upgrade-Insecure-Requests", "1");
            connect.header("Sec-Fetch-User", "?1");
            connect.header("Sec-Fetch-Site", "none");
            connect.header("Sec-Fetch-Mode", "navigate");
            connect.header("Host", "www.quyuege.com");
            //connect.header("dnt:","1");
            connect.header("Referer",url);
            connect.header("Cookie", "fikker-VyZm-8GxU=HyTKb10yZ9RUVcXwxUOLD1M7XUMYV16L; fikker-VyZm-8GxU=HyTKb10yZ9RUVcXwxUOLD1M7XUMYV16L");
            connect.header("Connection", "keep-alive");
            connect.header("Cache-Control", "max-age=0");
            connect.header("Accept-Language", "zh-CN,zh;q=0.9");
            connect.header("Accept-Encoding", "gzip, deflate, br");
            connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");


            //获取html数据
            Document document = connect.get();


            //获取文章标题
            Elements se = document.select("div[id=breadcrumb] a");
            ArrayList<String> l = new ArrayList<String>();
            for (Element element : se) {
                String bt = element.text();
                l.add(bt);
            }

            System.out.println(l.get(1));

            /*创建字符串输出流*/
            /*true的意思是在文件内容追加，false是覆盖原内容*/
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\xs\\" + l.get(1) + ".txt", true));


            Elements select = document.select("div[class=bd] div[class=page-body]");
            //获取章节目录
            Elements select1 = select.select("div[class=page-body] h1");
            String text1 = select1.text();
            System.out.println(select1.text());

            /*将章节添加进指定文件*/
            bufferedOutputStream.write(text1.getBytes());
            /*进行换行操作*/
            bufferedOutputStream.write("\r\n".getBytes());
            /*刷新一下*/
            bufferedOutputStream.flush();


            //获取文章数据
            Elements s = document.select("div[class=page-content] div[id=chapterContent]");
            String text = s.text();
            System.out.println(text);

            /*将章节添加进指定文件*/
            bufferedOutputStream.write(text.getBytes());
            /*进行换行操作*/
            bufferedOutputStream.write("\r\n".getBytes());
            /*刷新一下*/
            bufferedOutputStream.flush();

            /*关闭输出流*/
            bufferedOutputStream.close();


            //获取下一章地址
            Elements urls = document.select("div[class=papgbutton]  a");
            ArrayList<String> ll = new ArrayList<String>();

            //共有三条数据，地址值在第三
            for (Element element : urls) {
                String href = element.attr("href");
                ll.add(href);
                // System.out.println(href);
            }

            String a1 = ll.get(2);
            String[] split = a1.split("/");

            // System.out.println(ss);
            //判断是否有下一章数据
            if (split[4].equals(".html")) {
                url = null;
            } else {
                String ss = "https://www.quyuege.com" + a1;
                url = ss;
            }
        }
    }

}
