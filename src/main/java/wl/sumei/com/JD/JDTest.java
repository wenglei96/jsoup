package wl.sumei.com.JD;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wl.sumei.com.JD.dao.ProduceDao;
import wl.sumei.com.JD.pojo.Product;
import wl.sumei.com.JD.utils.HttpClientUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class JDTest {
    public static void main(String[] args) throws IOException, InterruptedException {


        //循环一百次，获取一百页内的数据
        //京东分页page为 1 3 5 7 .....
        //         对应第一页 第二页....
        for (int i = 0; i < 10; i++) {
//        1.指定URL路径
            String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&page=" + (2 * i - 1);

//        2.打开连接地址,获取HTML二进制数据
            Connection connect = Jsoup.connect(url);
            Document document = connect.get();
            // System.out.println(document.text());
//        3.解析手机列表
            Elements select = document.select("#J_goodsList>ul>li");

//        4.新建商品对象，用于封装数据
            Product product = new Product();

//        5.调用dao层，输入数据库表
            ProduceDao produceDao = new ProduceDao();

            if (select != null || select.size() != 0) {

                for (Element element : select) {
                    //3.1获取第一个手机的地址
                    String pid = element.attr("data-pid");
                    String url1 = "https://item.jd.com/" + pid + ".html";
                    System.out.println(pid);
                    product.setPid(pid);

                    //3.2打开手机连接，获取HTML文档
                    Connection connect1 = Jsoup.connect(url1);
                    Document document1 = connect1.get();
                    //System.out.println(url1);

                    //3.2.1 获取手机标题信息
                    String title = document1.select("div[class=sku-name]").get(0).text();
                    //System.out.println(title);
                    product.setTitle(title);

                    //3.2.2 获取手机品牌
                    Elements select1 = document1.select("#parameter-brand li");
                    String brand = select1.attr("title");
                    //System.out.println(brand);
                    product.setBrand(brand);

                    //3.2.3 获取手机名称
                    Elements select2 = document1.select("[class=parameter2 p-parameter-list] li:first-child");
                    String name = select2.attr("title");
                    //System.out.println(name);
                    product.setPname(name);

                    //3.2.4 获取价格
                    //拼接价格页面url 经过测试 返回Json数据  jd对IP进行了限制,加入pduid为随机数,是为了可以获取更多数据,但是依然只能爬取部分
                    String priceUrl = "https://p.3.cn/prices/mgets?pduid=" + Math.random() + "&skuIds=J_" + pid;

                    String priceJson = HttpClientUtils.doGet(priceUrl);
                    System.out.println(priceJson);
                    Gson gson = new GsonBuilder().create();
                    List<Map<String,String>> list = gson.fromJson(priceJson, List.class);
                    String price = list.get(0).get("p");
                    System.out.println(price);
                    product.setPrice(price);
                    //5.1 调用dao对象方法
                    produceDao.addInsert(product);
                }
            }
        }
    }
}
