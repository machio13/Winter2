package simwinter;

import simwinter.master.MasterCsvReader;
import simwinter.master.Stock;
import simwinter.trade.Trade;
import simwinter.trade.TradeCsvReader;
import simwinter.trade.TradeSide;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class Checks {

    public static boolean isNameCheck (File marketFile, String name) {
        List<Stock> stockersList = MasterCsvReader.readMarketCsv(marketFile);
        for (Stock stock : stockersList) {
            if (stock.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTickerCheck (File marketFile, String ticker) {
        List<Stock> stockersList = MasterCsvReader.readMarketCsv(marketFile);
        for (Stock stock : stockersList) {
            if (stock.getTicker().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    public static long quantityCheck(String ticker, LocalDateTime time, File tradeFile) {
        List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeFile);
        long totalQuantity = 0;
        for (Trade trade : tradeList) {
            if (trade.getTradeTicker().equals(ticker) && time.isAfter(trade.getTradedDatetime())) {
                if (trade.getTradeSide() == TradeSide.Buy) {
                    totalQuantity += trade.getTradeQuantity();
                }else if (trade.getTradeSide() == TradeSide.Sell){
                    totalQuantity -= trade.getTradeQuantity();
                }
            }
        }
        return totalQuantity;
    }

    //　時間が未来になっていないか確認する↓
    public static boolean tradedDatetimeCheck(String ticker, LocalDateTime userInputTime, File tradeFile) {
        List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeFile);

        LocalDateTime maxTradedDatetime = null;
        for (Trade trade : tradeList) {
            if (trade.getTradeTicker().equals(ticker)) {
                if (maxTradedDatetime == null || trade.getTradedDatetime().isAfter(maxTradedDatetime)) {
                    maxTradedDatetime = trade.getTradedDatetime();
                }
            }
        }

        // 該当するティッカーが見つからなかった場合、userInputTime を返す
        if (maxTradedDatetime == null) {
            return true;
        }
        else if (userInputTime.isAfter(maxTradedDatetime)) {
            System.out.println("入力時間：" + userInputTime);
            System.out.println("最新時間：" + maxTradedDatetime);
            return true;
        }
        return false;
    }

    public static boolean sideCheck(TradeSide side) {

        if (side.equals(TradeSide.Buy)) {
            return true;
        }
        return false;
    }
}
