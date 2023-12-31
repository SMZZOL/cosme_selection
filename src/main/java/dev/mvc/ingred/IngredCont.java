package dev.mvc.ingred;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;

@Controller
public class IngredCont {
	@Autowired
	@Qualifier("dev.mvc.ingred.IngredProc")
	private IngredProcInter ingredproc;
	
	  @Autowired
	  @Qualifier("dev.mvc.admin.AdminProc") 
	  private AdminProcInter adminProc = null;

@RequestMapping(value="/ingred/create.do", method=RequestMethod.POST)
public ModelAndView create(IngredVO ingredvo) {
	ModelAndView mav = new ModelAndView();

	this.ingredproc.insert_ingred(ingredvo);
	mav.setViewName("redirect:/");
	return mav;
}
@RequestMapping(value="/ingred/create.do", method=RequestMethod.GET)
public ModelAndView create(HttpSession session) {
	ModelAndView mav = new ModelAndView();
	
	 if (this.adminProc.isAdmin(session) == true) {
		 mav.setViewName("/ingred/create_ingred");
	 }else {
	     mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
	   }
	
	return mav;
}
} 
