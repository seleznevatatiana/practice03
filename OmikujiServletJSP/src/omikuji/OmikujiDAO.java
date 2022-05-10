package omikuji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBManager;

public class OmikujiDAO {

    //SQL文を準備
    public static String SQL_SELECT_COUNT_FROM_OMIKUJI = "SELECT COUNT (*) AS CNT FROM omikuji";
    public static String SQL_INSERT_OMIKUJI = "INSERT INTO omikuji  (omikuji_id, unsei_id, negaigoto, akinai, gakumon, updater, updated_date, creator, created_date) VALUES (?, ?, ?, ?, ?, ?, current_timestamp, ?, current_timestamp) ";
    public static String SQL_SELECT_OMIKUJI = "SELECT u.unsei_name, o.negaigoto, o.akinai, o.gakumon, o.updater, o.updated_date, o.creator, o.created_date  FROM omikuji o INNER JOIN unseimaster u ON o.unsei_id = u.unsei_id WHERE o.omikuji_id = ?";

    /**
     * おみくじテーブルのデータ件数を取得
     * @return count データ件数
     */
    public static int selectCountFromOmikuji()  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {

            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_FROM_OMIKUJI);
            // SQL文を実行
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("cnt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // ResultSetをクローズ
                DBManager.close(resultSet);
                // Statementをクローズ
                DBManager.close(preparedStatement);
                // DBとの接続を切断
                DBManager.close(connection);
            }
            catch (Exception e) {
            }
        }
        return count;
    }

    /**
     * おみくじテーブルにデータ登録
     * @return count データ件数
     */
    public static int insertOmikuji(String[] data) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String omikujiId = null;
        int count = 0;

        try {
            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(SQL_INSERT_OMIKUJI);
            //csvReaderを呼び出す
            //入力値をバインド
            preparedStatement.setString(1, data[2]);
            preparedStatement.setString(2, data[1]);
            preparedStatement.setString(3, data[3]);
            preparedStatement.setString(4, data[4]);
            preparedStatement.setString(5, data[5]);
            preparedStatement.setString(6, "タチアナ");
            preparedStatement.setString(7, "タチアナ");

            //代入演算子を使ってカウント
            count += preparedStatement.executeUpdate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // ResultSetをクローズ
                DBManager.close(resultSet);
                // Statementをクローズ
                DBManager.close(preparedStatement);
                // DBとの接続を切断
                DBManager.close(connection);
            }
            catch (Exception e) {
            }
        }
        return count;
    }

    /**
     * おみくじテーブルから一つの結果を取得
     * @return omikuji インスタンス
     */
    public static Omikuji selectFromOmikuji(Omikuji omikuji, String omikujiId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        omikujiId = null;

        try {

            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(SQL_SELECT_OMIKUJI);
            //入力値をバインド
            preparedStatement.setString(1, omikujiId);
            // SQL文を実行
            ResultSet resultSet2 = null;
            resultSet2 = preparedStatement.executeQuery();

            //resultsetから値の取り出し方
            while (resultSet2.next()) {
                omikuji = BirthdayInputServlet.getInstance(resultSet2.getString("unsei_name"));
                omikuji.setUnsei();
                omikuji.setOmikujiId(omikujiId);
                omikuji.setNegaigoto(resultSet2.getString("negaigoto"));
                omikuji.setAkinai(resultSet2.getString("akinai"));
                omikuji.setGakumon(resultSet2.getString("gakumon"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // ResultSetをクローズ
                DBManager.close(resultSet);
                // Statementをクローズ
                DBManager.close(preparedStatement);
                // DBとの接続を切断
                DBManager.close(connection);
            }
            catch (Exception e) {
            }
        }
        return omikuji;
    }
}
