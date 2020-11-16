package BtapChua.Models;

public class Books {
    private int bookID;
    private String name;
    private double price;
    private int qty;
    private byte status;

    public Books() {
        bookID = 0;
        name = "";
        price = 0.0;
        qty = 0;
        status = 0;
    }

    public Books(int bookID, String name, double price, int qty) {
        setBookID(bookID);
        setName(name);
        setPrice(price);
        setQty(qty);
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("BookId=%d\tName=%s\tPrice=%.2f\tQty=%d\tStatus=%d", this.bookID, this.name,
                this.price, this.qty, this.status);
    }
}
