package comparable;
public class Laptop  implements Comparable<Laptop>{

    private String brandName;
    private int price;
    private int ram;

    public Laptop(String brandName, int price, int ram) {
        this.brandName = brandName;
        this.price = price;
        this.ram = ram;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "comparable.Laptop{" +
                "brandName='" + brandName + '\'' +
                ", price=" + price +
                ", ram=" + ram +
                '}';
    }

    @Override
    public int compareTo(Laptop o) {
        if(this.getPrice()> o.getPrice())
            return 1;
        else if (this.getPrice()< o.getPrice())
            return -1;
        else
            return 0;
    }
}
