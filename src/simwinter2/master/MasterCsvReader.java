package simwinter2.master;

import generalsimwinter.master.Market;
import generalsimwinter.master.Stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MasterCsvReader {

    public static List<generalsimwinter.master.Stock> readMarketCsv(File marketFile) {
        List<generalsimwinter.master.Stock> stockersList = new ArrayList<>();
        String lineSplit = ",";
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(marketFile))){

            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] wordBox = line.split(lineSplit);

                generalsimwinter.master.Market updateMarket = Market.isRename(wordBox[2]);
                BigDecimal updateSharesIssued = new BigDecimal(wordBox[3]);

                stockersList.add(new Stock(wordBox[0], wordBox[1], updateMarket, updateSharesIssued));
            }
        }catch (IOException e) {
            System.out.println("ファイルが正常に読み込めない。");
        }catch (NumberFormatException e) {
            System.out.println("ファイル内の数値に異常があります。");
        }
        return stockersList;
    }
}
