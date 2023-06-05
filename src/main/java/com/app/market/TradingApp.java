package com.app.market;

import com.app.market.service.FileServiceImpl;
import com.app.market.service.TradingServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.List;

public class TradingApp {


    public static void main(String[] args) throws FileNotFoundException {


        System.out.println("Starting App");
        String filePath = "/Users/ritigupta/Downloads/stockmarket/src/main/java/com/app/market/data.txt";
        List<String[]> orders =  getOrdersFromFile(filePath);
        matchAndExecuteBuyOrder(orders);
//        tradingServiceImpl.addSellOrder("#1","BAC",200.0,100, LocalTime.now());
//        tradingServiceImpl.addSellOrder("#2","BAC",500.0,100, LocalTime.now());
//
//        tradingServiceImpl.matchAndExecuteBuyOrder("#3","BAC",238.10,90,LocalTime.now());
//        tradingServiceImpl.matchAndExecuteBuyOrder("#4","BAC",137.80 ,90,LocalTime.now());

    }


    private static List<String[]> getOrdersFromFile(String filePath) throws FileNotFoundException {
        FileServiceImpl fileServiceImpl = new FileServiceImpl(filePath);
        return fileServiceImpl.fileReader();
    }

    private static void matchAndExecuteBuyOrder(List<String[]> orders){
        TradingServiceImpl tradingServiceImpl = new TradingServiceImpl();
        for (String[] data : orders) {
            String orderId = data[0];
            LocalTime time = LocalTime.parse(data[1]);
            String stockname = data[2];
            String type = data[3];
            Double price = Double.parseDouble(data[4]);
            int quantity = Integer.parseInt(data[5]);

            if (type.equals("buy")) {
                tradingServiceImpl.matchAndExecuteBuyOrder(orderId, stockname, price, quantity, time);
            } else {
                tradingServiceImpl.addSellOrder(orderId, stockname, price, quantity, time);
            }
        }
    }
}