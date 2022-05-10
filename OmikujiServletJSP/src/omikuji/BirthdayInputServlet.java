package omikuji;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String omikujiId = null;
        Omikuji omikuji = null;

        omikujiId = ResultDAO.selectFromResult(birthday, uranaiDate);
        //結果がない場合はomikujiテーブルのデータチェックを実施
        if (omikujiId == null) {
            int count= OmikujiDAO.selectCountFromOmikuji();
                    //データがない場合はcsvファイルから取得
                    if (count == 0) {
                         count = CSVReader.csvRead();
                    }

                    //データがなかった場合
                    if (omikujiId.isEmpty()) {

                        //ランダム表示
                        int num = new Random().nextInt(count + 1);
                        omikujiId = Integer.toString(num);
                    }
        }
        //ランダムで引かれたomikujiIdを取得
        omikuji = OmikujiDAO.selectFromOmikuji(omikuji);
        //resultテーブルにデータを登録
        ResultDAO.insertResult(birthday, uranaiDate, omikujiId);

            OmikujiBean bean = new OmikujiBean();
            bean.setUnsei(omikuji.getUnsei());
            bean.setNegaigoto(omikuji.getNegaigoto());
            bean.setAkinai(omikuji.getAkinai());
            bean.setGakumon(omikuji.getGakumon());

            request.setAttribute("omikujiBean", bean);
            request.getRequestDispatcher("/WEB-INF/Omikuji.jsp").forward(request, response);
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