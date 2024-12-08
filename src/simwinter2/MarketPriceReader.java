package simwinter2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MarketPriceReader {

    public static List<MarketPrice> readeMarketPrice(File marketPrice) {
        List<MarketPrice> marketPriceList = new ArrayList<>();

        String lineSplit = ",";
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(marketPrice))){
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordBox = line.split(lineSplit);

                String ticker = wordBox[0];
                BigDecimal price = new BigDecimal(wordBox[1]);

                marketPriceList.add(new MarketPrice(ticker, price));
            }
        } catch (IOException e) {
            System.out.println("ファイルが正常に読み込めません。");
        }
        return marketPriceList;
    }
}
