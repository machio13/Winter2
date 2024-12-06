package simwinter;

import simwinter.trade.Trade;
import simwinter.trade.TradeSide;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class PositionNewInput {

    public static List<Position> newPosition(List<Trade> tradeList, List<MarketPrice> marketPriceList) {
        List<Position> positionList = new ArrayList<>();

        BigDecimal averageUnitPrice;
        BigDecimal realizeProfitAndLoss;
        BigDecimal valuation = null;
        BigDecimal unrealizedProfitAndLoss = null;

        Map<String, MarketPrice> marketPriceMap = marketPriceList.stream().collect(Collectors.toMap(MarketPrice::getTicker, mp -> mp));

        for (Trade trade : tradeList) {
            boolean check = false;
            for (Position position : positionList) {
                if (position.getTicker().equals(trade.getTradeTicker())) {
                    check = true;
                    MarketPrice marketPrice = marketPriceMap.get(trade.getTradeTicker());
                    BigDecimal tradeUnitPrice = trade.getTradedUnitPrice();

                    if (trade.getTradeSide().equals(TradeSide.Buy)) {
                        BigDecimal averageUnitPrice2 = position.getAverageUnitPrice();
                        BigDecimal tradeUnitPrice2 = trade.getTradedUnitPrice();
                        BigDecimal positionQuantity = new BigDecimal(position.getQuantity());
                        BigDecimal tradeQuantity = new BigDecimal(trade.getTradeQuantity());

                        BigDecimal totalCost = positionQuantity.multiply(averageUnitPrice2).add(tradeQuantity.multiply(tradeUnitPrice2));
                        BigDecimal totalQuantity = positionQuantity.add(tradeQuantity);

                        position.averageUnitPrice = totalCost.divide(totalQuantity, 2, RoundingMode.HALF_UP); //平均取得単価の変更
                        position.quantity += trade.getTradeQuantity(); //所有数量の変更

                    } else {
                        BigDecimal tradeQuantity = new BigDecimal(trade.getTradeQuantity());
                        BigDecimal positionAverageUnitPrice = position.getAverageUnitPrice();

                        if (position.getTicker().equals(marketPrice.getTicker())) {
                            BigDecimal element = tradeUnitPrice.subtract(positionAverageUnitPrice);

                            position.realizedProfitAndLoss = tradeQuantity.multiply(element); //実現損益の変更
                            position.quantity -= trade.getTradeQuantity(); //所有数量の変更
                        }
                    }
                }
            }
            if (!check) {
                MarketPrice marketPrice = marketPriceMap.get(trade.getTradeTicker());
                if (marketPrice != null) {
                    long initialQuantity = trade.getTradeSide().equals(TradeSide.Buy) ? trade.getTradeQuantity() : -trade.getTradeQuantity();
                    realizeProfitAndLoss = BigDecimal.ZERO;
                    averageUnitPrice = trade.getTradedUnitPrice();
                    Position position = new Position(trade.getTradeTicker(), trade.getTradeName(), initialQuantity, averageUnitPrice, realizeProfitAndLoss, valuation, unrealizedProfitAndLoss);
                    positionList.add(position);
                } else if (marketPrice == null) {
                    long initialQuantity = trade.getTradeSide().equals(TradeSide.Buy) ? trade.getTradeQuantity() : -trade.getTradeQuantity();
                    realizeProfitAndLoss = BigDecimal.ZERO;
                    averageUnitPrice = trade.getTradedUnitPrice();
                    Position position = new Position(trade.getTradeTicker(), trade.getTradeName(), initialQuantity, averageUnitPrice, realizeProfitAndLoss, valuation, unrealizedProfitAndLoss);
                    positionList.add(position);
                }
            }
        }
        Collections.sort(positionList, Comparator.comparing(Position::getTicker));
        return positionList;
    }
}
