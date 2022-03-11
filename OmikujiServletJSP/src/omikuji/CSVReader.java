package omikuji;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CSVReader {

    public static void csvRead()
            throws ClassNotFoundException, SQLException, IOException {

        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        //読み込みファイルのインスタンス生成
        //ファイル名を指定する
        fi = new FileInputStream("/OmikujiServletJSP/src/omikuji/fortune.csv");
        is = new InputStreamReader(fi);
        br = new BufferedReader(is);

        // readLineで一行ずつ読み込む
        String line; // 読み込み行
        String[] data; // 分割後のデータを保持する配列

        while ((line = br.readLine()) != null) {
            // lineをカンマで分割し、配列dataに設定
            data = line.split(",");
        }
    }
}
