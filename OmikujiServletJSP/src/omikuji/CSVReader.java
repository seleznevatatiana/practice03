package omikuji;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CSVReader {

    public static void csvRead() {

        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        try {

        //読み込みファイルのインスタンス生成
        //ファイル名を指定する
        fi = new FileInputStream("/OmikujiServletJSP/src/omikuji/fortune.csv");
        is = new InputStreamReader(fi);
        br = new BufferedReader(is);

        // readLineで一行ずつ読み込む
        String line; // 読み込み行
        String[] data = null; // 分割後のデータを保持する配列

        String omikujiId = "";
        int count = 0;

        while ((line = br.readLine()) != null) {
            // lineをカンマで分割し、配列dataに設定
            data = line.split(",");
        }
        omikujiDAO.insertOmikuji(count, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
