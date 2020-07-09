package com.acme.mytrader;

import com.acme.mytrader.domain.Stock;

public class StockBrokerMainApplication {
    public static void main(String[] args) {
        //Initialize Master Broker
        MasterStockBroker masterStockBroker = new MasterStockBroker();
        masterStockBroker.initialize();

        //"Person A" buys a stock with a strategy
        String personName = "Person A";
        PersonalStock personalAStock = new PersonalStock(personName);
        Stock stock = new Stock("APPLE", 100.00, 10);
        stock.setupTradingStrategy(80.0, 120.00, 10);
        personalAStock.buy(stock);
        masterStockBroker.personBuyStock(personalAStock);

        //"Person A" sells a stock
        stock = new Stock("APPLE", 100.00, 1);
        personalAStock.sell(stock);
        masterStockBroker.personSellStock(personalAStock);

        //Price increased for a stock, automatic decision SELL happens here
        masterStockBroker.priceUpdate("APPLE", 122.0);

        //Price decreased for a stock, automatic decision BUY happens here
        masterStockBroker.priceUpdate("APPLE", 79.0);

    }
}
