package org.example.model;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<OrderItem> items;
    private double grandTotal;    
    private String paymentStatus;

    public Order(int id, int userId, List<OrderItem> items, double grandTotal, String paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.grandTotal = grandTotal;
        this.paymentStatus = paymentStatus;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public List<OrderItem> getItems() { return items; }
    public double getGrandTotal() { return grandTotal; }
    public String getPaymentStatus() { return paymentStatus; }
}