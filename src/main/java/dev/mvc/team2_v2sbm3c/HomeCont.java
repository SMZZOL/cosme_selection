package dev.mvc.team2_v2sbm3c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeCont {
	
	
	  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	  public ModelAndView home() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/main");  // /WEB-INF/views/main.jsp
	    
	    return mav;
	  }
}
