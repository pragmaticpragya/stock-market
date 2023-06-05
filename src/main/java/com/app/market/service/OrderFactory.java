package com.app.market.service;

import com.app.market.constants.OrderType;
import com.app.market.model.BuyOrder;
import com.app.market.model.Order;
import com.app.market.model.SellOrder;

import java.time.LocalTime;

public class OrderFactory {
    public static Order getOrder(OrderType orderType, String orderId, String stockName, Double price, int quantity,
                                 LocalTime time){
        if(orderType == OrderType.BUY){
            return new BuyOrder(orderId,time,  stockName, price,quantity);
        } else if (orderType == OrderType.SELL) {
            return new SellOrder(orderId,time,  stockName, price,quantity);
        }
        return null;
    }
}
