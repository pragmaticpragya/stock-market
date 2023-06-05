package com.app.market.service;

import java.time.LocalTime;

public interface TradingService {

    void addSellOrder(String orderId, String stockName, Double price, int quantity, LocalTime time);

    void matchAndExecuteBuyOrder(String orderId, String stockName, Double price, int quantity, LocalTime time);
}
