package com.app.market.service;

import com.app.market.constants.OrderType;
import com.app.market.dao.ExchangeDao;
import com.app.market.model.BuyOrder;
import com.app.market.model.SellOrder;
import com.app.market.utils.FileService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TradingServiceImpl implements TradingService {

    ExchangeDao exchangeDao = new ExchangeDao();
    FileService fileService = new FileService();
    public void addSellOrder(String orderId, String stockName, Double price, int quantity, LocalTime time) {
        SellOrder order = (SellOrder) OrderFactory.getOrder(OrderType.SELL, orderId, stockName, price, quantity, time);
        exchangeDao.addSellOrder(order);
    }

    @Override
    public void matchAndExecuteBuyOrder(String orderId, String stockName, Double price, int quantity, LocalTime time) {
        BuyOrder order = (BuyOrder) OrderFactory
                .getOrder(OrderType.BUY, orderId, stockName, price, quantity, time);
        executeOrder(order);
    }

    private void executeOrder(BuyOrder buy) {
        PriorityQueue<SellOrder> queue = exchangeDao.getSellOrderPriorityQueue();
        List<SellOrder> sellOrdersList = new ArrayList<>();
        while (!queue.isEmpty()) {
            SellOrder sell = queue.peek();
            if (sell.getPrice() > buy.getPrice()) {
                break;
            } else {
                sellOrdersList.add(0, queue.poll());
            }
        }

        // now we have got the list in higher value less than the current buy
        int buyQuantity = buy.getQuantity();
        for (SellOrder sell : sellOrdersList) {
            if (sell.getQuantity() > buyQuantity) {
                int sellQuantity = sell.getQuantity() - buyQuantity;
                sell.setQuantity(sellQuantity);
                fileService.executeOrders(buy.getId(), sell.getPrice(), buyQuantity, sell.getId());
                break;
            } else {
                if (sell.getQuantity() <= buyQuantity) {
                    buyQuantity -= sell.getQuantity();
                    fileService.executeOrders(buy.getId(), sell.getPrice(), sell.getQuantity(), sell.getId());
                    sell.setQuantity(-1);
                }
            }
        }

        // add back the list to queue for future
        for (SellOrder sell : sellOrdersList) {
            if (sell.getQuantity() != -1) {
                exchangeDao.addSellOrder(sell);
            }
        }
    }
}
