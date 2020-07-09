package com.acme.mytrader.strategy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
    private double highPriceLimit;
    private double lowPriceLimit;
    private int sellOrBuyVolume;
    final static Log logger = LogFactory.getLog(TradingStrategy.class);

    public TradingStrategy(double highPriceLimit, double lowPriceLimit, int sellOrBuyVolume) {
        this.lowPriceLimit = lowPriceLimit;
        this.highPriceLimit = highPriceLimit;
        this.sellOrBuyVolume = sellOrBuyVolume;
    }

    public int getSellOrBuyVolume() {
        return sellOrBuyVolume;
    }

    public TradingStrategyDecisionEnum getTradingStrategyDecisionOnPriceChange(double changedPrice) {
        if (changedPrice <= lowPriceLimit) {
            logger.debug("Stock strategy on price change : " + changedPrice + " , decision : " + TradingStrategyDecisionEnum.BUY);
            return TradingStrategyDecisionEnum.BUY;
        } else if(changedPrice > highPriceLimit) {
            logger.debug("Stock strategy on price change : " + changedPrice + " , decision : " + TradingStrategyDecisionEnum.SELL);
            return TradingStrategyDecisionEnum.SELL;
        }
        logger.debug("Stock strategy on price change : " + changedPrice + " , decision : " + TradingStrategyDecisionEnum.DO_NOTHING);
        return TradingStrategyDecisionEnum.DO_NOTHING;
    }
}
