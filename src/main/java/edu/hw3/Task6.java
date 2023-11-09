package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6 {

    public record Stock(String name, Integer price) {}

    interface StockMarket {
        void add(Stock stock);

        void remove(Stock stock);

        Stock moseValuableStock();
    }

    public static class StockMarketImpl implements StockMarket {
        Comparator<Stock> stockComparator = Comparator.comparing(o -> o.price);

        PriorityQueue<Stock> stockPriorityQueue = new PriorityQueue<>(stockComparator.reversed());

        @Override
        public void add(Stock stock) {
            stockPriorityQueue.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            if (!stockPriorityQueue.isEmpty()) {
                stockPriorityQueue.remove(stock);
            }
        }

        @Override
        public Stock moseValuableStock() {
            return stockPriorityQueue.peek();
        }
    }
}
