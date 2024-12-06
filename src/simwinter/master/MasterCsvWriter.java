package simwinter.master;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class MasterCsvWriter {

    public void writeMarket(File masterFile) {
        String name = MasterValidation.addName(masterFile);
        String ticker = MasterValidation.addTicker(masterFile);
        Market market = MasterValidation.addMarket();
        BigDecimal sharesIssued = MasterValidation.addSharesIssued();

        Stock stock = new Stock(ticker, name, market, sharesIssued);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(masterFile, true))) {
            bufferedWriter.write(stock.getTicker() + "," + stock.getName() + "," + stock.getMarket().getFirstChar() + "," + stock.getSharesIssued());
            bufferedWriter.newLine();

        }catch (IOException e) {
            System.out.println("ファイルが正常に読み込めませんでした。");
        }
    }
}
