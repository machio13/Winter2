package simwinter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MakeValuation {

    public static BigDecimal isAcquisitionCost(long positionQuantity, BigDecimal averageUnitPrice) {
        BigDecimal quantity = new BigDecimal(positionQuantity);
        BigDecimal acquisitionCost = quantity.multiply(averageUnitPrice);
        return acquisitionCost;
    }

    public static BigDecimal isValuation(Position position, List<MarketPrice> marketPriceList) {
        Map<String, MarketPrice> marketPriceMap = marketPriceList.stream().collect(Collectors.toMap(MarketPrice::getTicker, mp -> mp));

        MarketPrice marketPrice = marketPriceMap.get(position.getTicker());
        if (marketPrice == null) {
            System.out.println("Market price not found for ticker: " + position.getTicker());
            return BigDecimal.ZERO; // 該当する時価がない場合は0を返す
        }

        BigDecimal quantity = new BigDecimal(position.getQuantity());
        return quantity.multiply(marketPrice.getPrice());
    }

}
