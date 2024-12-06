package simwinter.master;

import simwinter.Checks;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

public class MasterValidation {
    static Scanner scanner = new Scanner(System.in);

    public static String addName(File marketFile) {
        String userInput = "";
        boolean check = true;

        while (check) {
            System.out.print("銘柄名>");
            userInput = scanner.nextLine();
            if (Checks.isNameCheck(marketFile, userInput)) {
                System.out.println("既に記入済み");
            }else if (userInput.matches("[a-zA-Z0-9 .()]*")) {
                check = false;
            }else {
                System.out.println("使えない文字があります。やり直し。");
            }
        }
        return userInput;
    }

    public static String addTicker(File marketFile) {
        String userInput = "";
        boolean check = true;

        while (check) {
            System.out.print("銘柄コード>");
            userInput = scanner.nextLine();
            if (Checks.isTickerCheck(marketFile, userInput)) {
                System.out.println("同じ銘柄コードが既に記入済み。");
            }
            else if (userInput.matches("^\\d{4}$|^[0-9][0-9ACDFGHJKLMNPRSTUWXY][0-9][0-9ACDFGHJKLMNPRSTUWXY]")) {
                check = false;
            }else {
                System.out.println("正しく入力し直してください。");
            }
        }
        return userInput;
    }

    public static Market addMarket() {
        Market userInput = null;
        String userInputStr = "";
        boolean check = true;
        while (check) {
            System.out.print("上場市場(Prime:P, Standard:S, Growth:G)>");
            userInputStr = scanner.nextLine();
            switch (userInputStr) {
                case "P" -> {
                    userInput = Market.Prime;
                    check = false;
                }
                case "S" -> {
                    userInput = Market.Standard;
                    check = false;
                }
                case "G" -> {
                    userInput = Market.Growth;
                    check = false;
                }
                default -> {
                    System.out.println("記入が正しくありません。やり直し。");
                    check = true;
                }
            }
        }
        return userInput;
    }

    public static BigDecimal addSharesIssued() {
        String userInputStr = "";
        BigDecimal userInput = null;
        boolean check = true;
        while (check) {
            System.out.print("発行済み株式数>");
            userInputStr = scanner.nextLine();
            try {
                if (userInputStr.length() < 13) {
                    userInput = new BigDecimal(userInputStr);
                    if (userInput.compareTo(BigDecimal.ZERO) > 0) {
                        check = false;
                    }else {
                        System.out.println("0より大きい数で指定してください。");
                    }
                }else {
                    System.out.println("12字以内で書き直してください。");
                }
            }catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        }
        return userInput;
    }
}
