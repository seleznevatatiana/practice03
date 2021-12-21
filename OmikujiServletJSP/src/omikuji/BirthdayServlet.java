package omikuji;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/omikuji")

public class BirthdayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 日本語を表示するので、charsetにUTF-8を指定
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();


        // htmlを出力
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>おみくじ占い</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:pink \">");
        out.println("<h3 style=\"color:purple\">運勢を占ってみましょう！</h3>");
        out.println("<form action=\"omikujiInputBirthday\" method=\"post\">");
        out.println("誕生日を入力してください：");
        out.println("<input type=\"text\" name=\"birthday\" placeholder=\"19910101\">");
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (errorMsg != null) {
          out.println("<p>" + errorMsg + "</p>");
        }
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"送信\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }
}
