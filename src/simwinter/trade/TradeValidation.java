package simwinter.trade;

import simwinter.Checks;

import java.io.File;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class TradeValidation {
    static Scanner scanner = new Scanner(System.in);

    public static LocalDateTime addTradeTime(String ticker, File tradeFile) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd/HH:mm").withResolverStyle(ResolverStyle.STRICT);
        String userInputStr = "";
        LocalDateTime userInput = null;
        LocalDateTime today = LocalDateTime.now();
        boolean check = true;
        while (check){
            System.out.print("取引日時(yyyy-MM-dd/HH:mm)>");
            userInputStr = scanner.nextLine();
            try{
                userInput = LocalDateTime.parse(userInputStr, formatter);
                DayOfWeek dayOfWeek = userInput.getDayOfWeek();

                if (Checks.tradedDatetimeCheck(ticker, userInput, tradeFile)) {

                    if (userInput.isBefore(today) || userInput.equals(today)) {
                        switch (dayOfWeek) {
                            case SATURDAY, SUNDAY -> {
                                System.out.println("土日で時間外");
                            }
                            default -> {
                                if (userInput.getHour() >= 9 && userInput.getHour() <= 14) {
                                    check = false;
                                } else if (userInput.getHour() == 15 && userInput.getMinute() <= 30) {
                                    check = false;
                                } else {
                                    System.out.println("平日ですが時間外です。");
                                }
                            }
                        }
                    } else {
                        System.out.println("日付が未来になっています。");
                    }
                }else {
                    System.out.println("最新の取引時間より前の時間を入力しています。不可能。");
                }
            }catch (DateTimeParseException e) {
                System.out.println("フォーマット通り記入し直して");
            }
        }
        return userInput;
    }

    public static String addTicker(File masterFile) {
        boolean check = true;
        String userInput = "";
        while (check) {
            System.out.print("銘柄コード>");
            userInput = scanner.nextLine();
            if (Checks.isTickerCheck(masterFile, userInput)) {
                System.out.println("正常な入力です");
                check = false;
            }else {
                System.out.println("記入されている銘柄名が見つかりません。");
            }
        }
        return userInput;
    }

    public static TradeSide addSide(String ticker, LocalDateTime time, File tradeFile) {
        String userInputStr = "";
        TradeSide userInput = null;
        boolean check = true;
        while (check) {
            System.out.print("売買区分(Sell or Buy)>");
            userInputStr = scanner.nextLine();
            switch (userInputStr) {
                case "Sell" -> {
                    userInput = TradeSide.Sell;
                    long checkQuantity = Checks.quantityCheck(ticker, time, tradeFile);
                    System.out.println(checkQuantity);
                    if (checkQuantity > 0) {
                        check = false;
                    }else {
                        System.out.println("保有数が0以下です。");
                    }
                }
                case "Buy" -> {
                    userInput = TradeSide.Buy;
                    check = false;
                }
                default -> {
                    System.out.println("売買区分を正しく入力してください。");
                }
            }
        }return userInput;
    }

    public static long addQuantity(String ticker,LocalDateTime time, File tradeFile, TradeSide side) {
        String userInputStr = "";
        long userInput = 0;
        boolean check = true;
        while (check) {
            System.out.print("数量(100株単位)>");
            userInputStr = scanner.nextLine();
            try {
                userInput = Long.parseLong(userInputStr);
                if (userInput % 100 == 0 && userInput > 0) {
                    long checkQuantity = Checks.quantityCheck(ticker, time, tradeFile);
                    if (Checks.sideCheck(side)) {
                        check = false;

                    }else {
                        if (userInput <= checkQuantity) {
                            check = false;
                        } else {
                            System.out.println("保有数が" + checkQuantity + "なので" + userInput + "は不可能です。");
                        }
                    }
                }else {
                    System.out.println("100株単位で入力してください");
                }
            }catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        }
        return userInput;
    }

    public static BigDecimal addUnitPrice() {
        String userInputStr = "";
        BigDecimal userInput = null;
        boolean check = true;
        while (check) {
            System.out.print("取引単価(小数第二位まで可能)>");
            userInputStr = scanner.nextLine();
            try {
                if (userInputStr.matches("^\\d+(\\.\\d{1,2})?$")) {
                    userInput = new BigDecimal(userInputStr);
                    if (userInput.compareTo(BigDecimal.ZERO) > 0) {
                        check = false;
                    }else {
                        System.out.println("0以下は入力できません。やり直してください。");
                    }
                }else {
                    System.out.println("小数第二位まで入力可能です。");
                }
            }catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        }
        return userInput;
    }

    public static LocalDateTime addInputDatetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        String today = formatter.format(now);
        LocalDateTime Datetime = LocalDateTime.parse(today, formatter);
        System.out.println("入力日時；" + today);
        System.out.println("ーーー入力完了ーーー");
        return Datetime;
    }
}