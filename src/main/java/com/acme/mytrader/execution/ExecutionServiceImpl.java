package com.acme.mytrader.execution;

import com.acme.mytrader.domain.Stock;
import com.acme.mytrader.strategy.TradingStrategy;
import com.acme.mytrader.strategy.TradingStrategyDecisionEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class ExecutionServiceImpl implements ExecutionService {

    Map<String, Stock> stockMap = new HashMap<>();
    final static Log logger = LogFactory.getLog(ExecutionServiceImpl.class);

    @Override
    public void buy(Stock stock) {
        stockMap.put(stock.getSecurity(), stock);
    }

    @Override
    public void sell(Stock stock) {
        stockMap.get(stock.getSecurity()).setVolume(stockMap.get(stock.getSecurity()).getVolume() - stock.getVolume());
    }

    @Override
    public void priceUpdate(String security, double price) {
        TradingStrategy tradingStrategy = stockMap.get(security).getTradingStrategy();
        TradingStrategyDecisionEnum tradingStrategyDecisionEnum = tradingStrategy.getTradingStrategyDecisionOnPriceChange(price);
        switch (tradingStrategyDecisionEnum) {
            case SELL:
                logger.debug("price Update: " + price + " :: decision: " + TradingStrategyDecisionEnum.SELL + " :: selling stock " + security + " of volume " + tradingStrategy.getSellOrBuyVolume());
                this.sell(new Stock(security, price, tradingStrategy.getSellOrBuyVolume()));
                break;
            case BUY:
                logger.debug("price Update: " + price + " :: decision: " + TradingStrategyDecisionEnum.BUY + " :: buying stock " + security + " of volume " + tradingStrategy.getSellOrBuyVolume());
                this.buy(new Stock(security, price, tradingStrategy.getSellOrBuyVolume()));
                break;
            case DO_NOTHING:
                logger.debug("price Update: " + price + " :: decision: " + TradingStrategyDecisionEnum.DO_NOTHING + " :: nothing to do!");
                break;
        }
    }
}
