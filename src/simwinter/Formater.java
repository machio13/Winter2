package simwinter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Formater {

    public static String isBigDecimalFormat(BigDecimal sharesIssued) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(sharesIssued);
    }

    public static String isLongFormat(long quantity) {
        BigDecimal updateQuantity = new BigDecimal(quantity);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(updateQuantity);
    }

    public static String isUnitPriceFormat(BigDecimal unitPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(unitPrice);
    }

    public static String isDatetimeFormat(LocalDateTime time) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        return formater.format(time);
    }
}
