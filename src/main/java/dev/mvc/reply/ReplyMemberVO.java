package dev.mvc.reply;

public class ReplyMemberVO {
 /** 아이디 */
  private String id = "";
  
  /** 댓글 번호 */
  private int replyno;
  /** 관련 글 번호 */
  private int fboardno;
  /** 회원 번호 */
  private int memberno;
  /** 내용 */
  private String content;
  /** 패스워드 */
  private String passwd;
  /** 등록일 */
  private String rdate;
	  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getReplyno() {
    return replyno;
  }
  public void setReplyno(int replyno) {
    this.replyno = replyno;
  }
	public int getFboardno() {
		return fboardno;
	}
	public void setFboardno(int fboardno) {
		this.fboardno = fboardno;
	}
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}


