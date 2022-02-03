package db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import omikuji.Omikuji;

public class DBController {
    /**
     * 全件表示
     *
     * @throws ClassNotFoundException
     *             ドライバクラスが存在しない場合に送出
     * @throws SQLException
     *             データベース操作時にエラーが発生した場合に送出
     */
    public static void selectFromResult(String birthday, String uranaiDate, String omikujiId)
            throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //Resultテーブルから誕生日と占い日が一致する場合
        // DBに接続
        connection = DBManager.getConnection();
        // ステートメントを作成
        preparedStatement = connection.prepareStatement(ConstantSQL.SQL_SELECT_FROM_RESULT);
        //入力値をバインド
        preparedStatement.setString(1, birthday);
        preparedStatement.setString(2, uranaiDate);
        // SQL文を実行
        ResultSet rs = null;
        rs = preparedStatement.executeQuery();
        if (rs == null) {
        }

        while (rs.next()) {
            omikujiId = rs.getString("omikuji_id");
        }
    }

    public static void getFortune(String birthday, String uranaiDate, String omikujiId, String akinai)
            throws ClassNotFoundException, SQLException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

    Omikuji omikuji = null;

    //ファイル読み込みで使用する３つのクラス
    FileInputStream fi = null;
    InputStreamReader is = null;
    BufferedReader br = null;


        // DBに接続
        connection = DBManager.getConnection();
        // ステートメントを作成
        preparedStatement = connection.prepareStatement(ConstantSQL.SQL_SELECT_COUNT_FROM_OMIKUJI);
        // SQL文を実行
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt("cnt");

        if (count == 0) {

            //読み込みファイルのインスタンス生成
            //ファイル名を指定する
            fi = new FileInputStream("src/omikuji/fortune.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);

            // readLineで一行ずつ読み込む
            String line; // 読み　込み行
            String[] data; // 分割後のデータを保持する配列

            while ((line = br.readLine()) != null) {
                // lineをカンマで分割し、配列dataに設定
                data = line.split(",");

                // DBに接続
                connection = DBManager.getConnection();
                // ステートメントを作成
                preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT_OMIKUJI);
                //入力値をバインド
                preparedStatement.setString(1, data[2]);
                preparedStatement.setString(2, data[1]);
                preparedStatement.setString(3, data[3]);
                preparedStatement.setString(4, data[4]);
                preparedStatement.setString(5, data[5]);
                preparedStatement.setString(6, "タチアナ");
                preparedStatement.setString(7, "タチアナ");

                // SQL文を実行
                //代入演算子を使ってカウント
                count += preparedStatement.executeUpdate();
            }
        }
        //データがなかった場合
        if (omikujiId.isEmpty()) {

            //ランダム表示
            int num = new Random().nextInt(count + 1);
            omikujiId = Integer.toString(num);
        }

        // DBに接続
        connection = DBManager.getConnection();
        // ステートメントを作成
        preparedStatement = connection.prepareStatement(ConstantSQL.SQL_SELECT_OMIKUJI);
        //入力値をバインド
        preparedStatement.setString(1, omikujiId);
        // SQL文を実行
        ResultSet resultSet2 = null;
        resultSet2 = preparedStatement.executeQuery();

        //resultsetから値の取り出し方
        while (resultSet2.next()) {
//            omikuji = getInstance(resultSet2.getString("unsei_name"));
            omikuji.setUnsei();
            omikuji.setOmikujiId(omikujiId);
            omikuji.setNegaigoto(resultSet2.getString("negaigoto"));
            omikuji.setAkinai(resultSet2.getString("akinai"));
            omikuji.setGakumon(resultSet2.getString("gakumon"));

            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT_RESULT);
            //入力値をバインド
            //getを使う
            preparedStatement.setString(1, uranaiDate);
            preparedStatement.setString(2, birthday);
//            preparedStatement.setString(3, omikuji.omikujiId);
            preparedStatement.setString(4, "タチアナ");
            preparedStatement.setString(5, "タチアナ");

            // SQL文を実行
            int cnt4 = preparedStatement.executeUpdate();
        }
    }
}



