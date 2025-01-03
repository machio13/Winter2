package simwinter2;

import simwinter2.trade.Trade;
import simwinter2.trade.TradeSide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PositionInput {

    public static List<Position> sumPosition(List<Trade> tradeList) {
        List<Position> positionList = new ArrayList<>();

        for (Trade trade : tradeList) {
            boolean check = false;
            for (Position position : positionList) {
                if (trade.getTradeTicker().equals(position.getTicker())) {
                    check = true;
                    if (trade.getTradeSide().equals(TradeSide.Buy)) {
                        position.quantity += trade.getTradeQuantity();
                    } else {
                        position.quantity -= trade.getTradeQuantity();
                    }
                }
            }
            if (!check) {
                long initialQuantity = trade.getTradeSide().equals(TradeSide.Buy) ? trade.getTradeQuantity() : -trade.getTradeQuantity();
                Position position = new Position(trade.getTradeName(), trade.getTradeTicker(), initialQuantity);
                positionList.add(position);
            }
        }
        Collections.sort(positionList, Comparator.comparing(Position::getTicker));
        return positionList;
    }
}
