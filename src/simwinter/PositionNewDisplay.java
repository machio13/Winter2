package simwinter;

import java.math.BigDecimal;
import java.util.List;

public class PositionNewDisplay extends CutName{

    public void allShowPosition(List<Position> positionList, List<MarketPrice> marketPriceList) {

        String formattedValuation;
        String formattedUnrealizedPnL;

        System.out.println("|========================================================================================================================================================|");
        System.out.println("| Ticker | Name                          | Quantity        | Average Unit Price | Realized Profit And Loss | Valuation      | Unrealized Profit And Loss |");
        System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------|");

        for (Position position : positionList) {
            String ticker = position.getTicker();
            String name = isCutName(position.getName());
            long quantity = position.getQuantity();
            BigDecimal average = position.getAverageUnitPrice();
            BigDecimal realizePnL = position.getRealizedProfitAndLoss().setScale(0, BigDecimal.ROUND_HALF_DOWN);

            BigDecimal valuation = MakeValuation.isValuation(position, marketPriceList);// 各銘柄の評価額を計算
            BigDecimal acquisitionCost = MakeValuation.isAcquisitionCost(position.getQuantity(), average); // 取得価額を計算
            BigDecimal unrealizedPnL = valuation.subtract(acquisitionCost); // 未実現損益を計算

            String formattedAverage = Formater.isBigDecimalFormat(average);
            String formattedRealizePnL = Formater.isBigDecimalFormat(realizePnL);

            if (valuation.equals(BigDecimal.ZERO)) {
                formattedValuation = "N/A";
                formattedUnrealizedPnL = "N/A";
            }else {
                formattedValuation = Formater.isBigDecimalFormat(valuation);
                formattedUnrealizedPnL = Formater.isBigDecimalFormat(unrealizedPnL);
            }

            System.out.printf("|  %4s  | %-29s | %15s | %18s | %24s | %14s | %26s |\n",
                    ticker, name, quantity, formattedAverage, formattedRealizePnL, formattedValuation, formattedUnrealizedPnL); // 結果を出力
        }
        System.out.println("|========================================================================================================================================================|");
    }

    @Override
    public int cutNum() {
        return 29;
    }
}

//実現損益＝売却数量＊(売却価格ー取得単価)
//買い付け後の平均取得単価＝（買い付け前の保有数量＊平均取得単価＋買い付けた数量＊取得単価）/ 買い付け前の保有数量＋取得単価
//        保有するポジションを平均取得単価で価値算出したものを取得価額
//        取得価額＝保有数量＊平均取得価格

//        時価と保有数量を掛け合わせることで、「仮にこの金融商品をすぐに時価で売却したときに得られる金額」が得られる。これを評価結果とする方法が「時価を用いた評価」である。
//        評価額＝保有数量＊時価

//        評価額と取得価額の差額を評価損益または未実現損益という。
//        評価損益＝評価額ー取得価額
