package omikuji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBManager;

public class resultDAO {

    //SQL文を準備
    public static String SQL_SELECT_FROM_RESULT = "SELECT omikuji_id FROM result WHERE birthday = ? AND uranai_date =?";
    public static String SQL_INSERT_RESULT = "INSERT INTO result VALUES (?, ?, ?, ?, current_timestamp, ?, current_timestamp)";

    public static String selectFromResult(String birthday, String uranaiDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String omikujiId ="";

        try {

            //Resultテーブルから誕生日と占い日が一致する場合
            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_RESULT);
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
                e.printStackTrace();
            }
        }
        return omikujiId;
    }

    public static String insertResult(String birthday, String uranaiDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String omikujiId ="";

        try {

            // DBに接続
            connection = DBManager.getConnection();
            // ステートメントを作成
            preparedStatement = connection.prepareStatement(SQL_INSERT_RESULT);
            //入力値をバインド
            //getを使う
            preparedStatement.setString(1, uranaiDate);
            preparedStatement.setString(2, birthday);
            preparedStatement.setString(3, omikujiId);
            preparedStatement.setString(4, "タチアナ");
            preparedStatement.setString(5, "タチアナ");

            // SQL文を実行
            int cnt4 = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return omikujiId;
    }
}
