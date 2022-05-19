package omikuji;


import java.text.SimpleDateFormat;

public class CheckBirthday {

    /**
     * 日付文字列が正しい日付かチェック
     * @param str ymd
     * @return true:正しい日付　false:不正な日付
     */
    public boolean checkBirthday(String birthday) {
      try{
          if (birthday == null || !birthday.matches("[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])") {
              return false;
          }
        // 日付チェック
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        sdf.parse(birthday);

        return true;

      }catch(Exception ex){
        return false;
      }
    }
//
//    public static boolean checkBirthday (String birthday) {
//        if (birthday == null || !birthday.matches("[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])") {
//            return false;
//        }
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        df.setLenient(false);
//        try {
//            df.parse(birthday);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

}
