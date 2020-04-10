package wl.sumei.com.JD.pojo;

public class Product {
    private String pid;
    private String title;
    private String brand;
    private String pname;
    private String price;

    public Product() {
    }

    public Product(String pid, String title, String brand, String pname, String price) {
        this.pid = pid;
        this.title = title;
        this.brand = brand;
        this.pname = pname;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                '}';
    }

}
