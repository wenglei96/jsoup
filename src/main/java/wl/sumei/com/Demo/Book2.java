package wl.sumei.com.Demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Book2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://www.quyuege.com/xs/38/38589/6804591.html";
        String urlsz ="";

        while (true) {
            try {
                //创建一个缓冲池
                ExecutorService pool = Executors.newCachedThreadPool();
                //设置其容量为9
                pool = Executors.newFixedThreadPool(9);

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
                    connect.header("Referer",urlsz);
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

                    //System.out.println(l.get(1));

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
                    //System.out.println();
                    // System.out.println(a1+" "+split[4]);

                    // System.out.println(ss);
                    //判断是否有下一章数据
                    if (split[4].equals(".html")) {
                        url = null;
                    } else {
                        String ss = "https://www.quyuege.com" + a1;
                        urlsz ="https://www.quyuege.com"+ll.get(0);
                        url = ss;
                    }
                }
                break;


            }catch (Exception e){
                //请求次数过多，需要休眠一下
                System.out.println("服务器过载，休息10秒");
                Thread.sleep(10000);
                continue;
            }
        }
    }
}
