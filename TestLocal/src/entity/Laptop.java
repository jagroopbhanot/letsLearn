package entity;
public class Laptop {

    private String brandName;
    private Long price;
    private String hdd;

    public Laptop(String brandName, Long price, String hdd) {
        this.brandName = brandName;
        this.price = price;
        this.hdd = hdd;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brandName='" + brandName + '\'' +
                ", price=" + price +
                ", hdd='" + hdd + '\'' +
                '}';
    }
}
