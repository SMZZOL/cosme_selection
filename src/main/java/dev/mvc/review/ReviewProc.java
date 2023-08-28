package dev.mvc.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
	
	@Autowired
	private ReviewDAOInter ReviewDAO;
	
	public ArrayList<ReviewVO> review_by_cosmeno(int cosmeno){
		return this.ReviewDAO.review_by_cosmeno(cosmeno);
	}
	
	public int add_review (ReviewVO reviewvo) {
		return this.ReviewDAO.add_review(reviewvo);
	}
	
	public int check_member(ReviewVO reviewvo) {
		return this.ReviewDAO.check_member(reviewvo);
	}
	
	public String avg_grade(int cosmeno) {
		return this.ReviewDAO.avg_grade(cosmeno);
	}

}
