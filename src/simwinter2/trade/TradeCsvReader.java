package simwinter2.trade;

import generalsimwinter.trade.Trade;
import generalsimwinter.trade.TradeSide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TradeCsvReader {

    public static List<generalsimwinter.trade.Trade> readTradeCsv(File tradeFile) {
        List<generalsimwinter.trade.Trade> tradeList = new ArrayList<>();

        String lineSplit = ",";
        String line = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(tradeFile))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordBox = line.split(lineSplit);
                LocalDateTime replaceTDTime = LocalDateTime.parse(wordBox[0]);
                String ticker = wordBox[1];
                String name = wordBox[2];
                generalsimwinter.trade.TradeSide side = TradeSide.valueOf(wordBox[3]);
                long replaceQuantity = Long.parseLong(wordBox[4]);
                BigDecimal replaceUnitPrice = new BigDecimal(wordBox[5]);
                LocalDateTime replaceInputDatetime = LocalDateTime.parse(wordBox[6]);

                tradeList.add(new generalsimwinter.trade.Trade(replaceTDTime, ticker, name, side, replaceQuantity, replaceUnitPrice, replaceInputDatetime));
            }
        }catch (IOException e) {
            System.out.println("ファイルが正常に読み込めません。");
        }
        Collections.sort(tradeList, Comparator.comparing(Trade::getTradeTicker));
        return tradeList;
    }
}
