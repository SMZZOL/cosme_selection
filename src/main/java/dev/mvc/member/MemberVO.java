package dev.mvc.member;

public class MemberVO {
  
  private int userno;
  private String id = "";
  private String passwd = "";
  private String email = "";
  private String mname = "";
  private String mdate="";
  private int grade = 0;
  
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
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
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
