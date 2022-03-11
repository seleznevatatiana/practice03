package omikuji;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ConstantSQL;
import db.DBManager;

@WebServlet(urlPatterns = { "/omikujiInputBirthday" })

public class BirthdayInputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        // 日本語を表示するので、charsetにUTF-8を指定
        response.setContentType("text/html; charset=UTF-8");

        String birthday = request.getParameter("birthday");

        //誕生日の形式が正しいかどうかをチェックする
        boolean check;
        CheckBirthday checkBirthday = new CheckBirthday();
        check = checkBirthday.checkBirthday(birthday);

        if (!check) {
            request.setAttribute("errorMsg", "正しい生年月日を入力してください。");
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/omikuji");
            dispatcher2.forward(request, response);
          }


        //占い日を指定
        Date date = new Date(); // 今日の日付
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String uranaiDate = dateFormat.format(date);

        //omikujiIdの宣言
        String omikujiId = "";

        try {

        // 結果テーブルからデータを取り出す
       resultDAO.selectFromResult(birthday, uranaiDate, omikujiId);
        } catch (Exception e){

        }

        Omikuji omikuji = null;

        //ファイル読み込みで使用する３つのクラス
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

//            omikujiDAO.selectCountFromOmikuji();

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
                fi = new FileInputStream("/OmikujiServletJSP/src/omikuji/fortune.csv"); //csvReaderを呼び出す(omikujiBeanを使う)
                is = new InputStreamReader(fi);
                br = new BufferedReader(is);

                // readLineで一行ずつ読み込む
                String line; // 読み込み行
                String[] data; // 分割後のデータを保持する配列

                while ((line = br.readLine()) != null) {
                    // lineをカンマで分割し、配列dataに設定
                    data = line.split(",");

//                    omikujiDAO.insertOmikuji(String omikujiId, int count);

                    // DBに接続
                    connection = DBManager.getConnection();
                    // ステートメントを作成
                    preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT_OMIKUJI);//omikujiDAO
                  //csvReaderを呼び出す
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

            //omikujiDAO.selectFromOmikuji(Omikuji omikuji, String omikujiId)

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
                omikuji = getInstance(resultSet2.getString("unsei_name"));
                omikuji.setUnsei();
                omikuji.setOmikujiId(omikujiId);
                omikuji.setNegaigoto(resultSet2.getString("negaigoto"));
                omikuji.setAkinai(resultSet2.getString("akinai"));
                omikuji.setGakumon(resultSet2.getString("gakumon"));

                resultDAO.insertResult(birthday, uranaiDate, omikujiId);

            }

            OmikujiBean bean = new OmikujiBean();
            bean.setUnsei(omikuji.getUnsei());
            bean.setNegaigoto(omikuji.getNegaigoto());
            bean.setAkinai(omikuji.getAkinai());
            bean.setGakumon(omikuji.getGakumon());

            request.setAttribute("omikujiBean", bean);
            request.getRequestDispatcher("/WEB-INF/Omikuji.jsp").forward(request, response);

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //クラス分けしたらtry-catchを消す
            try{
            // ResultSetをクローズ
            DBManager.close(resultSet);
            // Statementをクローズ
            DBManager.close(preparedStatement);
            // DBとの接続を切断
            DBManager.close(connection);
        } catch (Exception e) {
         }
        }
    }

    //Omikujiクラスをnewするためのメソッド
    public static Omikuji getInstance(String unseimei) {
        Omikuji omikuji = null;
        switch (unseimei) {
        //大吉の場合
        case "大吉":
            omikuji = new Daikichi();
            break;

        //中吉の場合
        case "中吉":
            omikuji = new Chukichi();
            break;

        //小吉の場合
        case "小吉":
            omikuji = new Shokichi();
            break;

        //吉の場合
        case "吉":
            omikuji = new Kichi();
            break;

        //末吉の場合
        case "末吉":
            omikuji = new Suekichi();
            break;

        //凶の場合
        case "凶":
            omikuji = new Kyo();
            break;
        }
        return omikuji;

    }
}