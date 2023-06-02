package dev.mvc.cosmetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class cosmetypeCont {
	@Autowired
	@Qualifier("dev.mvc.cosmetype.cosmetypeProc")
	private cosmetypeProcInter cosmetypeproc;
	
	
	@RequestMapping(value="/cosmetype/create.do", method=RequestMethod.POST)
	public ModelAndView create(cosmetypeVO cosmetypevo) {
		ModelAndView mav = new ModelAndView();
		

		this.cosmetypeproc.insert_cosmetype(cosmetypevo);
		mav.setViewName("redirect:/");
		return mav;
	}
	@RequestMapping(value="/cosmetype/create.do", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/cosmetype/create_cosmetype");
		return mav;
	}
}
