package com.app.market;

import com.app.market.constants.OrderType;
import com.app.market.model.BuyOrder;
import com.app.market.service.TradingServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class TradingApp {


    public static void main(String[] args) throws FileNotFoundException {


        System.out.println("Starting App");

        readFromFile();

//        tradingServiceImpl.addSellOrder("#1","BAC",200.0,100, LocalTime.now());
//        tradingServiceImpl.addSellOrder("#2","BAC",500.0,100, LocalTime.now());
//
//        tradingServiceImpl.matchAndExecuteBuyOrder("#3","BAC",238.10,90,LocalTime.now());
//        tradingServiceImpl.matchAndExecuteBuyOrder("#4","BAC",137.80 ,90,LocalTime.now());

    }


    private static void readFromFile() throws FileNotFoundException {
        TradingServiceImpl tradingServiceImpl = new TradingServiceImpl();

        File orderFile = new File("/Users/ritigupta/Downloads/stockmarket/src/main/java/com/app/market/data.txt");
        Scanner scanner = new Scanner(orderFile);
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(" ");
            String orderId = data[0];

            LocalTime time = LocalTime.parse(data[1]);
            String stockname = data[2];
            String type = data[3];

            Double price = Double.parseDouble(data[4]);

            int quantity = Integer.parseInt(data[5]);

            if (type.equals("buy")){
                tradingServiceImpl.matchAndExecuteBuyOrder(orderId, stockname, price, quantity, time);
            }
            else {
                tradingServiceImpl.addSellOrder(orderId, stockname, price, quantity, time);

            }
        }

    }
}