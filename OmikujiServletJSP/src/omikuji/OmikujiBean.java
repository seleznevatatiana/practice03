package omikuji;

public class OmikujiBean {

    public String negaigoto;
    public String akinai;
    public String gakumon;
    public String unseiId;
    public String omikujiId;
    public String updater;
    public java.sql.Timestamp updatedDate;
    public String creator;
    public java.sql.Timestamp createdDate;

    public void setUnsei() {
    }

   public String unsei;
   /**
    * @return unsei
    */
   public String getUnsei() {
       return unsei;
   }
    /**
     * @param unsei セットする unsei
     */
    public void setUnsei(String unsei) {
        this.unsei = unsei;
    }
    /**
     * @return negaigoto
     */
    public String getNegaigoto() {
        return negaigoto;
    }
    /**
     * @param negaigoto セットする negaigoto
     */
    public void setNegaigoto(String negaigoto) {
        this.negaigoto = negaigoto;
    }
    /**
     * @return akinai
     */
    public String getAkinai() {
        return akinai;
    }
    /**
     * @param akinai セットする akinai
     */
    public void setAkinai(String akinai) {
        this.akinai = akinai;
    }
    /**
     * @return gakumon
     */
    public String getGakumon() {
        return gakumon;
    }
    /**
     * @param gakumon セットする gakumon
     */
    public void setGakumon(String gakumon) {
        this.gakumon = gakumon;
    }
    /**
     * @return unseiId
     */
    public String getUnseiId() {
        return unseiId;
    }
    /**
     * @param unseiId セットする unseiId
     */
    public void setUnseiId(String unseiId) {
        this.unseiId = unseiId;
    }
    /**
     * @return omikujiId
     */
    public String getOmikujiId() {
        return omikujiId;
    }
    /**
     * @param omikujiId セットする omikujiId
     */
    public void setOmikujiId(String omikujiId) {
        this.omikujiId = omikujiId;
    }
    /**
     * @return updater
     */
    public String getUpdater() {
        return updater;
    }
    /**
     * @param updater セットする updater
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }
    /**
     * @return updatedDate
     */
    public java.sql.Timestamp getUpdatedDate() {
        return updatedDate;
    }
    /**
     * @param updatedDate セットする updatedDate
     */
    public void setUpdatedDate(java.sql.Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }
    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }
    /**
     * @param creator セットする creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * @return createdDate
     */
    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }
    /**
     * @param createdDate セットする createdDate
     */
    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

//    public String disp() {
//        String disp;
//        disp = String.format(DISP_STR, this.unsei);
//
//        //StringBuilderでコンソール表示する文字列を作る
//          StringBuilder sb = new StringBuilder();
//          sb.append(disp);
//          sb.append(System.getProperty("line.separator"));
//          sb.append("願い事:"  + negaigoto);
//          sb.append(System.getProperty("line.separator"));
//          sb.append("商い:"  + akinai);
//          sb.append(System.getProperty("line.separator"));
//          sb.append("学問:"  + gakumon);
//
//        return sb.toString();
//        }

  }


