package simwinter;

import java.math.BigDecimal;

public class MarketPrice {
    private String ticker;
    private BigDecimal price;

    public MarketPrice(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
