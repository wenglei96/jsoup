package wl.sumei.com.JD.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import wl.sumei.com.JD.pojo.Product;

import java.beans.PropertyVetoException;

public class ProduceDao extends JdbcTemplate {

        public ProduceDao()  {
            ComboPooledDataSource source = new ComboPooledDataSource();
            try {

                source.setDriverClass("com.mysql.jdbc.Driver");
                source.setUser("root");
                source.setPassword("root");
                source.setJdbcUrl("jdbc:mysql://localhost:3306/crawler?characterEncoding=utf-8");

            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }

        super.setDataSource(source);
        }

        public void addInsert(Product product){
            super.update("insert into test values (?,?,?,?,?)",
                    product.getPid(),product.getTitle(),product.getPname(),product.getBrand(),product.getPrice());



        }


}
