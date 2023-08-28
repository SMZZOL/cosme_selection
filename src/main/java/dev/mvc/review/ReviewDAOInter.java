package dev.mvc.review;

import java.util.ArrayList;

public interface ReviewDAOInter {
	
	public ArrayList<ReviewVO> review_by_cosmeno(int cosmeno);
	
	public int add_review (ReviewVO reviewvo);
	
	public int check_member(ReviewVO reviewvo);
	
	public String avg_grade(int cosmeno);

}
