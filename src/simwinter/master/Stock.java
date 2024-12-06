package simwinter.master;

import java.math.BigDecimal;

public class Stock {
    private String ticker;
    private String name;
    private Market market;
    private BigDecimal sharesIssued;

    public Stock(String ticker, String name, Market market, BigDecimal sharesIssued) {
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.sharesIssued = sharesIssued;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public Market getMarket() {
        return market;
    }


    public BigDecimal getSharesIssued() {
        return sharesIssued;
    }
}
