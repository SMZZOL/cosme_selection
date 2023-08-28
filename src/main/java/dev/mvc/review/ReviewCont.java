package dev.mvc.review;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

@Controller
public class ReviewCont {
	
@Autowired
@Qualifier("dev.mvc.review.ReviewProc")
private ReviewProcInter reviewproc;

@Autowired
@Qualifier("dev.mvc.member.MemberProc")
private MemberProcInter memberProc = null;

@ResponseBody
@RequestMapping(value="/review/add_review.do", method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
public String add_review (ReviewVO reviewvo) {
	
	int cnt =this.reviewproc.add_review(reviewvo);
	String str="";
	ArrayList<ReviewVO> list= this.reviewproc.review_by_cosmeno(reviewvo.getCosmeno());
	for(ReviewVO Reviewvo : list) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate date = LocalDate.parse(Reviewvo.getReviewtime(), formatter);
		String dateOnly = date.toString();
		MemberVO membervo = this.memberProc.read(Reviewvo.getMemberno());
		String name = (membervo.getMname().charAt(0))+"**";

		str +="		<div class=\"comment\">\r\n"
				+ "			<div class=\"author\">"+name+"</div>\r\n"
				+"<div class=\"grade\">★"+Reviewvo.getReviewgrade()+"</div>"
				+ "			<div class=\"content\">"+Reviewvo.getReviewcontent()+"</div>\r\n"
				+ "			<div class=\"timestamp\">"+dateOnly+"</div>\r\n"
				+ "		</div>";
		
	}
	
	
    JSONObject obj = new JSONObject();
    obj.put("str",str);
	
    return obj.toString();
}

@ResponseBody
@RequestMapping(value="/review/check_member.do", method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
public String check_member (ReviewVO reviewvo) {
	int memberno =reviewvo.getMemberno();
	int cosmeno =reviewvo.getCosmeno();
	
	int cnt = this.reviewproc.check_member(reviewvo);
	
	System.out.println("카운트는 "+cnt);
;
    JSONObject obj = new JSONObject();
    obj.put("cnt",cnt);
	
    return obj.toString();
}


}
