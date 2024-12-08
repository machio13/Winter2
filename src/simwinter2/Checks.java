package simwinter2;

import simwinter2.master.MasterCsvReader;
import simwinter2.master.Stock;
import simwinter2.trade.Trade;
import simwinter2.trade.TradeCsvReader;
import simwinter2.trade.TradeSide;

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

    public static long isQuantityCheck(String ticker, LocalDateTime time, File tradeFile) {
        long totalQuantity = 0;
        List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeFile);
        for (Trade trade : tradeList) {
            if (trade.getTradeTicker().equals(ticker) && time.isAfter(trade.getTradedDatetime())) {
                if (trade.getTradeSide().equals(TradeSide.Buy)) {
                    totalQuantity += trade.getTradeQuantity();
                } else if (trade.getTradeSide().equals(TradeSide.Sell)) {
                    totalQuantity -= trade.getTradeQuantity();
                }
            }
        }
        return totalQuantity;
    }

    public static boolean isSideCheck(TradeSide side) {

        if (side.equals(TradeSide.Buy)) {
            return true;
        }
        return false;
    }

    public static boolean isTradeTimeCheck(String ticker, LocalDateTime userInputTime, File tradeFile) {
        List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeFile);

        LocalDateTime maxTradeTime = null;
        for (Trade trade : tradeList) {
            if (trade.getTradeTicker().equals(ticker)) {
                if (maxTradeTime == null || trade.getTradedDatetime().isAfter(maxTradeTime)) {
                    maxTradeTime = trade.getTradedDatetime();
                }
            }
        }

        if (maxTradeTime == null) {
            return true;
        }
        else if (userInputTime.isAfter(maxTradeTime)) {
            System.out.println("これまでの最新時間：" + maxTradeTime);
            System.out.println("今回の入力時間：" + userInputTime);
            return true;
        }
        return false;
    }
}
