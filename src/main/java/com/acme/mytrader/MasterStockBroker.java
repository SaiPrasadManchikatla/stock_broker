package com.acme.mytrader;

import com.acme.mytrader.domain.Stock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class MasterStockBroker {

    private Map<String, Stock> stockMap;
    private Map<String, PersonalStock> personalStockMap;
    final static Log logger = LogFactory.getLog(MasterStockBroker.class);

    public void initialize() {
        stockMap = new HashMap<>();
        stockMap.put("APPLE", new Stock("APPLE", 100.00, 100000));
        stockMap.put("GOOGLE", new Stock("GOOGLE", 99.00, 1000000));
        stockMap.put("YAHOO!", new Stock("YAHOO!", 50.00, 1000000));
        stockMap.put("GE", new Stock("GE", 35.50, 1000000));
        stockMap.put("FACEBOOK", new Stock("FACEBOOK", 23.33, 10000000));
        stockMap.put("FORD", new Stock("FORD", 29.98, 1000000));
        stockMap.put("NETFLIX", new Stock("NETFLIX", 98.00, 100000));
        stockMap.put("GOPRO", new Stock("GOPRO", 10.00, 100000));
        stockMap.put("BABA", new Stock("BABA", 11.00, 100000));
        stockMap.put("TESLA", new Stock("TESLA", 99.99, 1000000));
        personalStockMap = new HashMap<>();
    }

    public void addStock(Stock stock) {
        this.stockMap.put(stock.getSecurity(), stock);
    }

    private void removeStock(String security) {
        this.stockMap.remove(security);
    }

    private void buy(Stock stock) {
        if (stockMap.containsKey(stock.getSecurity())
                && stockMap.get(stock.getSecurity()).getVolume() - stock.getVolume() > 0) {
            stockMap.get(stock.getSecurity()).setVolume(stockMap.get(stock.getSecurity()).getVolume() - stock.getVolume());
        } else {
            new RuntimeException("No such volume available");
        }
        logger.debug("A stock " + stock.getSecurity() + " of volume " + stock.getVolume() + " was bought, remaining volume " + stockMap.get(stock.getSecurity()).getVolume());
    }

    private void sell(Stock stock) {
        stockMap.get(stock.getSecurity()).setVolume(stockMap.get(stock.getSecurity()).getVolume() + stock.getVolume());
        logger.debug("A stock " + stock.getSecurity() + " of volume " + stock.getVolume() + " was sold, remaining volume " + stockMap.get(stock.getSecurity()).getVolume());
    }

    /**
     * Inform price updates for all persons
     *
     * @param security
     * @param price
     */
    public void priceUpdate(String security, double price) {
        logger.debug("There is a price update for stock: " + security + " :: updated price: " + price);
        stockMap.get(security).setPricePerUnit(price);
        for (Map.Entry<String, PersonalStock> entrySet : personalStockMap.entrySet()) {
            entrySet.getValue().priceUpdate(security, price);
        }
    }

    public void personBuyStock(PersonalStock personalStock) {
        logger.debug("A person named " + personalStock.getPersonName() + " bought a stock: " + personalStock.getCurrentStock().getSecurity() + " :: volume: " + personalStock.getCurrentStock().getVolume());
        if (!personalStockMap.containsKey(personalStock.getPersonName())) {
            personalStockMap.put(personalStock.getPersonName(), personalStock);
        }
        this.buy(personalStock.getCurrentStock());
    }

    public void personSellStock(PersonalStock personalStock) {
        logger.debug("A person named " + personalStock.getPersonName() + " sold a stock: " + personalStock.getCurrentStock().getSecurity() + " :: volume: " + personalStock.getCurrentStock().getVolume());
        if (!personalStockMap.containsKey(personalStock.getPersonName())) {
            new RuntimeException("There are no stocks to sell for this person");
        }
        this.sell(personalStock.getCurrentStock());
    }
}
