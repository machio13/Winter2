package generalsimwinter.trade;

import generalsimwinter.CutName;
import generalsimwinter.Formater;

import java.util.List;

public class TradeCsvDisplay extends CutName {

    public void showTrade(List<Trade> tradeList) {
        System.out.println("|=========================================================================================================================|");
        System.out.println("| Traded Datetime  | Ticker | Name                            | Side | Quantity     | Trade Unit Price | Input Datetime   |");
        System.out.println("|------------------+--------+---------------------------------+------+--------------+------------------+------------------|");

        for (Trade trade : tradeList) {
            String tradedDatetime = Formater.isDatetimeFormat(trade.getTradedDatetime());
            String ticker = trade.getTradeTicker();
            String name = isCutName(trade.getTradeName());
            TradeSide side = trade.getTradeSide();
            String quantity = Formater.isLongFormat(trade.getTradeQuantity());
            String unitPrice = Formater.isUnitPriceFormat(trade.getTradedUnitPrice());
            String inputDatetime = Formater.isDatetimeFormat(trade.getInputDatetime());

            System.out.printf("| %16s |  %4s  | %-31s | %-4s | %12s | %16s | %15s |\n", tradedDatetime, ticker,  name, side, quantity, unitPrice, inputDatetime);
        }
        System.out.println("|=========================================================================================================================|");
    }

    @Override
    public int cutNum() {
        return 31;
    }
}
