package simwinter.trade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TradeInputName {
    public static String isEqualName(File masterFile, String ticker) {

        String lineSplit = ",";
        String line = "";
        String name = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(masterFile))){
             bufferedReader.readLine();
             while ((line = bufferedReader.readLine()) != null) {
                 String[] lineBox = line.split(lineSplit);
                 if (ticker.equals(lineBox[0])) {
                     name = lineBox[1];
                 }else {
                     System.out.println("");
                 }
             }
        }catch (IOException e) {
            System.out.println("ファイルが読み込めませんでした。");
        }
        return name;
    }
}