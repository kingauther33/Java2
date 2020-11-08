package giftshop;

public class GiftModel {
    private int id;
    private String name;
    private double price;
    private int qty;

    public GiftModel() {
        id = 0;
        name = "";
        price = 0;
        qty = 0;
    }

    public GiftModel(int id, String name, double price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

