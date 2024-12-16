package simwinter2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class NewPositionDisplay extends CutName{

    public void showPosition(List<Position> positionList, List<MarketPrice> marketPriceList) {
        System.out.println("|====================================================================================================================================|");
        System.out.println("| Ticker | Name                           | Quantity        | AverageUnitPrice | RealizedPnL     | Valuation       | UnrealizedPnL   |");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");

        String formattedValuation;
        String formattedUnrealizedPnL;

        for (Position position : positionList) {
            String ticker = position.getTicker();
            String name = isCutName(position.getName());
            long quantity = position.getQuantity();
            BigDecimal averageUnitPrice = position.getAverageUnitPrice();
            BigDecimal realizedPnL = position.getRealizedProfitAndLoss().setScale(0, RoundingMode.HALF_DOWN);

            BigDecimal valuation = MakeValuation.isValuationMake(position, marketPriceList);
            BigDecimal acquisitionCost = MakeValuation.isAcquisitionCost(quantity, averageUnitPrice);
            BigDecimal unrealizedPnL = valuation.subtract(acquisitionCost);

            String formattedQuantity = Formater.isLongFormat(quantity);
            String formattedAverageUnitPrice = Formater.isDecimalFormat(averageUnitPrice);
            String formattedRealizePnL = Formater.isDecimalFormat(realizedPnL);

            if (valuation.equals(BigDecimal.ZERO)) {
                formattedValuation = "N/A";
                formattedUnrealizedPnL = "N/A";
            }else {
                formattedValuation = Formater.isDecimalFormat(valuation);
                formattedUnrealizedPnL = Formater.isDecimalFormat(unrealizedPnL);
            }

            System.out.printf("|  %4s  | %-30s | %15s | %16s | %15s | %15s | %15s |\n", ticker, name, formattedQuantity, formattedAverageUnitPrice, formattedRealizePnL, formattedValuation, formattedUnrealizedPnL);
        }
        System.out.println("|----------------------------------------------------------------------------------------------------------------------------------|");
    }

    @Override
    public int cutNum() {
        return 30;
    }
}
