package simwinter2;

import java.util.List;

public class PositionDisplay extends CutName{

    public void showDisplay(List<Position> positionList) {
        System.out.println("|===============================================================|");
        System.out.println("| Ticker | Name                              | Quantity         |");
        System.out.println("|---------------------------------------------------------------|");

        for (Position position : positionList) {
            String ticker = position.getTicker();
            String name = isCutName(position.getName());
            String quantity = Formater.isLongFormat(position.getQuantity());

            System.out.printf("|  %4s  | %-33s | %16s |\n", ticker, name, quantity);
        }
        System.out.println("|===============================================================|");
    }

    @Override
    public int cutNum() {
        return 33;
    }
}
