package org.example.tableview;

public class Product {
    private String name;
    private String sku;
    private double price;
    private double total;
    private int quantity;

    public Product(String name, String sku, double price, int quantity) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setSku(String newSku) {
        sku = newSku;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

