package simwinter.contact;

import simwinter.*;
import simwinter.master.MasterCsvDisplay;
import simwinter.master.MasterCsvReader;
import simwinter.master.MasterCsvWriter;
import simwinter.master.Stock;
import simwinter.trade.Trade;
import simwinter.trade.TradeCsvDisplay;
import simwinter.trade.TradeCsvReader;
import simwinter.trade.TradeCsvWriter;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void menuSelect() {
        System.out.println("株式取引管理システムを開始します。");
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("操作するメニューを選んでください。");
            System.out.println("1. 銘柄マスタ一覧表示");
            System.out.println("2. 銘柄マスタ新規登録");
            System.out.println("3. 取引入力");
            System.out.println("4. 取引表示");
            System.out.println("5. Step6 保有ポジション表示");
            System.out.println("6. Step7 保有ポジション表示");
            System.out.println("9. アプリケーションを終了します");
            System.out.print("入力してください:");
            String userInput = scanner.nextLine();
            File marketCsvFile = new File("src/SimWinter/csvfile/Master.csv");
            File tradeCsvFile = new File("src/SimWinter/csvfile/TradeData.csv");
            File marketPriceFile = new File("src/simwinter/MarketPrice.csv");

            switch (userInput) {
                case "1" -> {
                    System.out.println("「銘柄マスタ一覧表示」が選択されました。");
                    List<Stock> marketCsvReader = MasterCsvReader.readMarketCsv(marketCsvFile);
                    MasterCsvDisplay marketDisplay = new MasterCsvDisplay();
                    marketDisplay.shouMarket(marketCsvReader);
                }
                case "2" -> {
                    System.out.println("「銘柄マスタ新規登録」が選択されました。");
                    MasterCsvWriter plusMarketWriter = new MasterCsvWriter();
                    plusMarketWriter.writeMarket(marketCsvFile);
                }
                case "3" -> {
                    System.out.println("「取引入力」が選択されました。");
                    TradeCsvWriter tradeWriter = new TradeCsvWriter();
                    tradeWriter.writeTrade(marketCsvFile, tradeCsvFile);
                }
                case "4" -> {
                    System.out.println("「取引表示」が選択されました。");
                    List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeCsvFile);
                    TradeCsvDisplay tradeDisplay = new TradeCsvDisplay();
                    tradeDisplay.showTrade(tradeList);
                }
                case "5" -> {
                    System.out.println("「保有ポジション表示」が選択されました。スッテプ６");
                    List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeCsvFile);
                    List<Position> positionList = PositionInput.sumPosition(tradeList);
                    PositionDisplay positionDisplay = new PositionDisplay();
                    positionDisplay.showPosition(positionList);
                }
                case "6" -> {
                    System.out.println("「保有ポジション表示」が選択されました。ステップ７");
                    List<Trade> tradeList = TradeCsvReader.readTradeCsv(tradeCsvFile);
                    List<MarketPrice> marketPriceList = MarketPriceReader.readMarketPrice(marketPriceFile);
                    List<Position> positionList = PositionNewInput.newPosition(tradeList, marketPriceList);
                    PositionNewDisplay positionNewDisplay = new PositionNewDisplay();
                    positionNewDisplay.allShowPosition(positionList, marketPriceList);
                }
                case "9" -> {
                    System.out.println("アプリケーションを終了します。");
                    System.out.println("---");
                    check = false;
                }
                default -> {
                    System.out.println("\"" + userInput + "\"に対応するメニューは存在しません。");
                    System.out.println("---");
                }
            }
        }
    }
}
