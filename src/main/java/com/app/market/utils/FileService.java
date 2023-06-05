package com.app.market.utils;

public class FileService {
    public void executeOrders(String buyOrderId, Double sellingPrice, int quantity, String sellerOrderID) {
        System.out.println(buyOrderId + " " + sellingPrice + " " + quantity + " " + sellerOrderID);
    }
}
