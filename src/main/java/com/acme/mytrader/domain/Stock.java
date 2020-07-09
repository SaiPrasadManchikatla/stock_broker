package com.acme.mytrader.domain;

import com.acme.mytrader.strategy.TradingStrategy;

import java.util.Objects;

public class Stock {
    private final String security;
    private double pricePerUnit;
    private int volume;
    private TradingStrategy tradingStrategy;

    public Stock(String security, double pricePerUnit, int volume) {
        this.security = security;
        this.pricePerUnit = pricePerUnit;
        this.volume = volume;
    }

    public void setupTradingStrategy(double lowPriceLimit, double highPriceLimit, int buyOrSellVolume) {
        this.tradingStrategy = new TradingStrategy(lowPriceLimit, highPriceLimit, buyOrSellVolume);
    }

    public TradingStrategy getTradingStrategy() {
        return tradingStrategy;
    }

    public String getSecurity() {
        return security;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(security, pricePerUnit, volume);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) o;
        return security == stock.security &&
                Objects.equals(pricePerUnit, stock.pricePerUnit) &&
                Objects.equals(volume, stock.volume);
    }

}
