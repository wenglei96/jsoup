package wl.sumei.com.Demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Book {
    public static void main(String[] args) throws IOException {
        /*指定网页路径*/

        String url = "https://read.qidian.com/chapter/vbbZ5MDpwd7ywypLIF-xfQ2/eSlFKP1Chzg1";

        /*循环判断*/
        while (url != null) {

            /*获取路径*/
            Document document = Jsoup.connect(url).get();

            /*获取电子书，章节标签*/
                String text1 = document.select("div[class=main-text-wrap]  h3[class=j_chapterName]").text();
            System.out.println(text1);

            /*创建字符串输出流*/
            /*true的意思是在文件内容追加，false是覆盖原内容*/
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\伏天氏.text", true));

            /*将章节添加进指定文件*/
            bufferedOutputStream.write(text1.getBytes());
            /*进行换行操作*/
            bufferedOutputStream.write("\r\n".getBytes());
            /*刷新一下*/
            bufferedOutputStream.flush();

            /*获取章节文件内容标签*/
            Elements select = document.select("div[class=read-content j_readContent] p");
            /*循环遍历所有信息*/
            for (Element element : select) {
                String text = element.text();

                /*将章节内容添加进指定文件*/
                bufferedOutputStream.write(text.getBytes());
                bufferedOutputStream.write("\r\n".getBytes());
                bufferedOutputStream.flush();

                /*打印输出章节内容*/
                System.out.println(text);
            }
            /*关闭输出流*/
            bufferedOutputStream.close();


            /*获取下一章地址*/
            Elements select1 = document.select("div[class=chapter-control dib-wrap] a[id=j_chapterNext]");

            /* "//read.qidian.com/chapter/SPAg82lbMBolePy7xMLiNw2/DEn0iCTCjzVp4rPq4Fd4KQ2"
             * 截取前缀*/
            String[] split = select1.attr("href").split("/");
            /*"//read.qidian.com/chapter"*/
            String u = "//" + split[2] + "/" + split[3];


            /*判断是否可以获取到下一章，*/
            if (u.equals("//read.qidian.com/chapter")) {
                /*可以则返回a标签的href属性地址*/
                url = "http:" + select1.attr("href");
            } else {
                /*获取不到，返回null结束循环*/
                url = null;
            }
        }
    }
}
