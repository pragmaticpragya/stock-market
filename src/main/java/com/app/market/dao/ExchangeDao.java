package com.app.market.dao;

import com.app.market.model.BuyOrder;
import com.app.market.model.Order;
import com.app.market.model.SellOrder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ExchangeDao {
    private PriorityQueue<SellOrder> sellOrdersQueue = new PriorityQueue<>(new Comparator<SellOrder>() {
        @Override
        public int compare(SellOrder o1, SellOrder o2){
            if(o1.getPrice() == o2.getPrice()){
                return o2.getQuantity() - o1.getQuantity();
            }
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    });
    private ArrayList<BuyOrder> executedBuyOrderQueue = new ArrayList<BuyOrder>();

    public PriorityQueue<SellOrder> getSellOrderPriorityQueue() {
        return sellOrdersQueue;
    }
    public void addSellOrder(SellOrder order){
        sellOrdersQueue.add(order);
    }

    public void addCompletedOrder(BuyOrder order){
        executedBuyOrderQueue.add(order);
    }
}
