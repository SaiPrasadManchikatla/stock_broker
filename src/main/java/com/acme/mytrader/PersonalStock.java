package com.acme.mytrader;

import com.acme.mytrader.domain.Stock;
import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;

public class PersonalStock {

    private String personName;
    ExecutionService executionService;
    private Stock currentStock;

    public PersonalStock(String personName) {
        this.personName = personName;
        executionService = new ExecutionServiceImpl();
    }

    public void buy(Stock stock) {
        executionService.buy(stock);
        this.currentStock = stock;
    }

    public void sell(Stock stock) {
        executionService.sell(stock);
        this.currentStock = stock;
    }

    public void priceUpdate(String security, double price) {
        executionService.priceUpdate(security, price);
    }

    public String getPersonName() {
        return personName;
    }

    public Stock getCurrentStock() {
        return currentStock;
    }

}
