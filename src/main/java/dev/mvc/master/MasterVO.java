package dev.mvc.master;

public class MasterVO {
  private int masterno;
  private String id ="";
  private String passwd = "";
  private String mname = "";
  private String mdate="";
  private int grade = 0;
  public int getMasterno() {
    return masterno;
  }
  public void setMasterno(int masterno) {
    this.masterno = masterno;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getMname() {
    return mname;
  }
  public void setMname(String mname) {
    this.mname = mname;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public int getGrade() {
    return grade;
  }
  public void setGrade(int grade) {
    this.grade = grade;
  }
  
  
}
