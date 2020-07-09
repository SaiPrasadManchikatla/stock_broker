package com.acme.mytrader.execution;

import com.acme.mytrader.domain.Stock;

public interface ExecutionService {
    void buy(Stock stock);
    void sell(Stock stock);
    void priceUpdate(String security, double price);
}
