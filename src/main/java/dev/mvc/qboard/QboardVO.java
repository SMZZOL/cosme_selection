package dev.mvc.qboard;

public class QboardVO {
  
  private int qboardno;
  
  private int memberno;
  
  private String qtitle = "";
  
  private String qcontent = "";
  
  private String rdate = "";

  public int getQboardno() {
    return qboardno;
  }

  public void setQboardno(int qboardno) {
    this.qboardno = qboardno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getQtitle() {
    return qtitle;
  }

  public void setQtitle(String qtitle) {
    this.qtitle = qtitle;
  }

  public String getQcontent() {
    return qcontent;
  }

  public void setQcontent(String qcontent) {
    this.qcontent = qcontent;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
  

}