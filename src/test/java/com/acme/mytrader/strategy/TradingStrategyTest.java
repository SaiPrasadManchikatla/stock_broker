package com.acme.mytrader.strategy;

import org.junit.Assert;
import org.junit.Test;

public class TradingStrategyTest {
    @Test
    public void testTradingStrategySell() {
        TradingStrategy tradingStrategyMock = new TradingStrategy(20.0, 10.0, 10);
        TradingStrategyDecisionEnum tradingStrategyDecisionEnum = tradingStrategyMock.getTradingStrategyDecisionOnPriceChange(22.0);
        Assert.assertEquals(TradingStrategyDecisionEnum.SELL, tradingStrategyDecisionEnum);

    }

    @Test
    public void testTradingStrategyBuy() {
        TradingStrategy tradingStrategyMock = new TradingStrategy(20.0, 10.0, 10);
        TradingStrategyDecisionEnum tradingStrategyDecisionEnum = tradingStrategyMock.getTradingStrategyDecisionOnPriceChange(5.0);
        Assert.assertEquals(TradingStrategyDecisionEnum.BUY, tradingStrategyDecisionEnum);
    }

    @Test
    public void testTradingStrategyDoNothing() {
        TradingStrategy tradingStrategyMock = new TradingStrategy(20.0, 10.0, 10);
        TradingStrategyDecisionEnum tradingStrategyDecisionEnum = tradingStrategyMock.getTradingStrategyDecisionOnPriceChange(15.0);
        Assert.assertEquals(TradingStrategyDecisionEnum.DO_NOTHING, tradingStrategyDecisionEnum);
    }
}
