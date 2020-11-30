package Main;

public class Product {
    private String name;
    private double price;

    static int productsCount;

    public Product(){productsCount++;}
    public Product(String name, double price){
        this.name = name;
        this.price = price;
        productsCount++;
    }

    public static int count(){
        return productsCount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
