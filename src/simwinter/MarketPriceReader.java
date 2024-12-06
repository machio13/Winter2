package simwinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MarketPriceReader {

    public static List<MarketPrice> readMarketPrice(File marketPriceFile) {
        List<MarketPrice> marketPriceList = new ArrayList<>();

        String lineSplit = ",";
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(marketPriceFile))){
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(lineSplit);

                String ticker = data[0];
                BigDecimal marketPrice = new BigDecimal(data[1]);

                marketPriceList.add(new MarketPrice(ticker, marketPrice));
            }
        }catch (IOException e) {
            System.out.println("ファイルが正常に読み込めませんでした。");
        }
        return marketPriceList;
    }
}
