package simwinter2;

import simwinter2.trade.Trade;
import simwinter2.trade.TradeSide;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class NewPositionInput {

    public static List<Position> NewInput(List<Trade> tradeList, List<MarketPrice> marketPriceList) {
        List<Position> positionList = new ArrayList<>();

        BigDecimal averageUnitPrice;
        BigDecimal realizedPnL;
        BigDecimal valuation = null;
        BigDecimal unrealizedPnL = null;

        Map<String, MarketPrice> marketPriceMap = marketPriceList.stream().collect(Collectors.toMap(MarketPrice :: getTicker, mp -> mp));

        for (Trade trade : tradeList) {
            boolean check = false;
            for (Position position : positionList) {
                if (trade.getTradeTicker().equals(position.getTicker())) {
                    check = true;
                    MarketPrice marketPrice = marketPriceMap.get(trade.getTradeTicker());
                    BigDecimal tradeUnitPrice = trade.getTradedUnitPrice();

                    if (trade.getTradeSide().equals(TradeSide.Buy)) {
                        BigDecimal beforeAverageUnitPrice = position.getAverageUnitPrice(); // newしてpositionに入っているUnitPrice
                        BigDecimal positionQuantity = new BigDecimal(position.getQuantity()); // newしてpositionに入っているQuantity
                        BigDecimal tradeQuantity = new BigDecimal(trade.getTradeQuantity()); // これから取引されるquantity

                        BigDecimal totalCost = positionQuantity.multiply(beforeAverageUnitPrice).add(tradeQuantity.multiply(tradeUnitPrice));
                        BigDecimal totalQuantity = positionQuantity.add(tradeQuantity);

                        position.averageUnitPrice = totalCost.divide(totalQuantity, 2, RoundingMode.HALF_UP);
                        position.quantity += trade.getTradeQuantity();

                    } else if (trade.getTradeSide().equals(TradeSide.Sell)) {
                        BigDecimal tradeQuantity = new BigDecimal(trade.getTradeQuantity());
                        BigDecimal positionUnitPrice = position.getAverageUnitPrice();

                        if (marketPrice == null || position.getTicker().equals(marketPrice.getTicker())) {
                            BigDecimal element = tradeUnitPrice.subtract(positionUnitPrice);

                            position.realizedProfitAndLoss = tradeQuantity.multiply(element);
                            position.quantity -= trade.getTradeQuantity();
                        }
                    }
                }
            }

            if (!check) {
                MarketPrice marketPrice = marketPriceMap.get(trade.getTradeTicker());
                if (marketPrice != null) {
                    long initialQuantity = trade.getTradeSide().equals(TradeSide.Buy) ? trade.getTradeQuantity() : -trade.getTradeQuantity();
                    realizedPnL = BigDecimal.ZERO;
                    averageUnitPrice = trade.getTradedUnitPrice();
                    Position position = new Position(trade.getTradeTicker(), trade.getTradeName(), initialQuantity, averageUnitPrice, realizedPnL, valuation, unrealizedPnL);
                    positionList.add(position);
                } else if (marketPrice == null) {
                    long initialQuantity = trade.getTradeSide().equals(TradeSide.Buy) ? trade.getTradeQuantity() : -trade.getTradeQuantity();
                    realizedPnL = BigDecimal.ZERO;
                    averageUnitPrice = trade.getTradedUnitPrice();
                    Position position = new Position(trade.getTradeTicker(), trade.getTradeName(), initialQuantity, averageUnitPrice, realizedPnL, valuation, unrealizedPnL);
                    positionList.add(position);
                }
            }
        }
        Collections.sort(positionList, Comparator.comparing(Position::getTicker));
        return positionList;
    }
}
