package dev.mvc.ingred;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IngredCont {
	@Autowired
	@Qualifier("dev.mvc.ingred.IngredProc")
	private IngredProcInter ingredproc;

@RequestMapping(value="/ingred/create.do", method=RequestMethod.POST)
public ModelAndView create(IngredVO ingredvo) {
	ModelAndView mav = new ModelAndView();
	

	this.ingredproc.insert_ingred(ingredvo);
	mav.setViewName("redirect:/");
	return mav;
}
@RequestMapping(value="/ingred/create.do", method=RequestMethod.GET)
public ModelAndView create() {
	ModelAndView mav = new ModelAndView();
	
	mav.setViewName("/ingred/create_ingred");
	return mav;
}
} 
