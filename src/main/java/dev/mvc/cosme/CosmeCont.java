package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CosmeCont {

	
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.GET)
	public ModelAndView list_by_type() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/cosme/list_by_type");
		
		
		return mav;
	}
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.POST)
	public String list_by_type_post(@RequestBody Map<String, Object> request) {

		
		ArrayList<String> list= (ArrayList<String>) request.get("list");
		if(list.isEmpty()) {
			System.out.println("empty");
		}
		for(String num : list) {
			System.out.println(num);
		}
	//	System.out.println(request);
//		System.out.println("들어와따!");
		
		
		return "반환성공";
	}
}
