package com.app.market.model;


import java.time.LocalTime;

abstract public class Order {

    private String id;
    private LocalTime time;
    private String symbol;
    private Double price;
    private int quantity;

    public Order (String id, LocalTime time, String symbol, Double price, int quantity){
        this.id = id;
        this.time = time;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void execute_order(){

    }
}

