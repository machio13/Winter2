package simwinter.trade;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeCsvWriter {
    public void writeTrade(File masterFile, File tradeFile) {

        String ticker = TradeValidation.addTicker(masterFile);
        LocalDateTime tradedDatetime = TradeValidation.addTradeTime();
        String name = "";
        TradeSide side = TradeValidation.addSide();
        long quantity = TradeValidation.addQuantity();
        BigDecimal tradeUnitPrice = TradeValidation.addUnitPrice();
        LocalDateTime inputDatetime = TradeValidation.addInputDatetime();

        Trade trade = new Trade(tradedDatetime, ticker, name, side, quantity, tradeUnitPrice, inputDatetime);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tradeFile, true))) {
            bufferedWriter.write(trade.getTradedDatetime() + "," + trade.getTradeTicker()
                    + "," + TradeInputName.isEqualName(masterFile, trade.getTradeTicker())
                    + "," + trade.getTradeSide() + "," + trade.getTradeQuantity()
                    + "," + trade.getTradedUnitPrice() + "," + trade.getInputDatetime());

            bufferedWriter.newLine();
        }catch (IOException e) {
            System.out.println("ファイルに正常に書き込めませんでした。");
        }
    }
}
