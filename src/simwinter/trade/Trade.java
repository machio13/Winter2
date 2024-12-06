package simwinter.trade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    private LocalDateTime tradedDatetime;
    private String tradeTicker;
    private String tradeName;
    private TradeSide tradeSide;
    private long tradeQuantity;
    private BigDecimal tradedUnitPrice;
    private LocalDateTime inputDatetime;

    public Trade(LocalDateTime tradedDatetime, String tradeTicker, String tradeName, TradeSide tradeSide, long tradeQuantity, BigDecimal tradedUnitPrice, LocalDateTime inputDatetime) {
        this.tradedDatetime = tradedDatetime;
        this.tradeTicker = tradeTicker;
        this.tradeName = tradeName;
        this.tradeSide = tradeSide;
        this.tradeQuantity = tradeQuantity;
        this.tradedUnitPrice = tradedUnitPrice;
        this.inputDatetime = inputDatetime;
    }

    public LocalDateTime getTradedDatetime() {
        return tradedDatetime;
    }

    public String getTradeTicker() {
        return tradeTicker;
    }

    public String getTradeName() {
        return tradeName;
    }

    public TradeSide getTradeSide() {
        return tradeSide;
    }

    public long getTradeQuantity() {
        return tradeQuantity;
    }

    public BigDecimal getTradedUnitPrice() {
        return tradedUnitPrice;
    }

    public LocalDateTime getInputDatetime() {
        return inputDatetime;
    }
}
