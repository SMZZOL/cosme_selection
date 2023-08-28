package dev.mvc.review;

public class ReviewVO {
	private int reviewno;
	private String reviewcontent;
	private String reviewtime;
	private int cosmeno;
	private int memberno;
	private String mname;
	private int reviewgrade;
	public int getReviewno() {
		return reviewno;
	}
	public String getMname() {
		return mname;
	}
	public int getReviewgrade() {
		return reviewgrade;
	}
	public void setReviewgrade(int reviewgrade) {
		this.reviewgrade = reviewgrade;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}
	public String getReviewcontent() {
		return reviewcontent;
	}
	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}
	public String getReviewtime() {
		return reviewtime;
	}
	public void setReviewtime(String reviewtime) {
		this.reviewtime = reviewtime;
	}
	public int getCosmeno() {
		return cosmeno;
	}
	public void setCosmeno(int cosmeno) {
		this.cosmeno = cosmeno;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}

}
