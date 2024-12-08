package simwinter2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MakeValuation {

    public static BigDecimal isValuationMake(Position position, List<MarketPrice> marketPriceList) {
        Map<String, MarketPrice> marketPriceMap = marketPriceList.stream().collect(Collectors.toMap(MarketPrice :: getTicker, mp -> mp));

        MarketPrice marketPrice = marketPriceMap.get(position.getTicker());
        if (marketPrice == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal quantity = new BigDecimal(position.getQuantity());
        return quantity.multiply(marketPrice.getPrice());
    }

    public static BigDecimal isAcquisitionCost(long positionQuantity, BigDecimal averageUnitPrice) {
        BigDecimal quantity = new BigDecimal(positionQuantity);
        return quantity.multiply(averageUnitPrice);
    }
}
