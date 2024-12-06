package simwinter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Position {
    String name;
    String ticker;
    long quantity; //保有数量
    BigDecimal averageUnitPrice; //平均取得単価
    BigDecimal realizedProfitAndLoss; //実現損益
    BigDecimal acquisitionCost; //取得価額
    BigDecimal valuation; //評価額
    BigDecimal unrealizedProfitAndLoss; //評価損益

    public Position(String name, String ticker,long quantity) {
        this.name = name;
        this.ticker = ticker;
        this.quantity = quantity;
    }

    public Position(String ticker, String name, long quantity, BigDecimal averageUnitPrice, BigDecimal realizedProfitAndLoss, BigDecimal valuation, BigDecimal unrealizedProfitAndLoss) {
        this.ticker = ticker;
        this.name = name;
        this.quantity = quantity;
        this.averageUnitPrice = averageUnitPrice;
        this.realizedProfitAndLoss = realizedProfitAndLoss;
        this.valuation = valuation;
        this.unrealizedProfitAndLoss = unrealizedProfitAndLoss;
    }

    public Position(String ticker, String name, long quantity, BigDecimal averageUnitPrice, BigDecimal acquisitionCost, BigDecimal realizedProfitAndLoss, BigDecimal valuation, BigDecimal unrealizedProfitAndLoss) {
        this.ticker = ticker;
        this.name = name;
        this.quantity = quantity;
        this.averageUnitPrice = averageUnitPrice;
        this.acquisitionCost = acquisitionCost;
        this.realizedProfitAndLoss = realizedProfitAndLoss;
        this.valuation = valuation;
        this.unrealizedProfitAndLoss = unrealizedProfitAndLoss;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public long getQuantity() {
        return quantity;
    }

    public BigDecimal getAverageUnitPrice() {
        return averageUnitPrice;
    }

    public BigDecimal getAcquisitionCost() {
        return acquisitionCost;
    }

    public BigDecimal getRealizedProfitAndLoss() {
        return realizedProfitAndLoss;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public BigDecimal getUnrealizedProfitAndLoss() {
        return unrealizedProfitAndLoss;
    }

//    public void addQuantity(long quantity) {
//        this.quantity += quantity;
//    }
//
//    public void minusQuantity(long quantity) {
//        this.quantity -= quantity;
//    }

}
