package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CosmeCont {
  
  public CosmeCont() {
    System.out.println("-> CosmeCont created.");
  }

	
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.GET)
	public ModelAndView list_by_type() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/cosme/list_by_type");
		
		
		return mav;
	}
	
	// http://localhost:9093/cosme/list_by_type.do 404
//	@PostMapping("/cosme/list_by_type.do")
	@ResponseBody
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.POST)
	public String listByTypePost(@RequestBody Map<String, Object> request) {

		ArrayList<String> list= (ArrayList<String>) request.get("list");
		if(list.isEmpty()) {
			System.out.println("empty");
		}
		for(String num : list) {
			System.out.print(num + ", ");
		}
//		System.out.println(request);
//		System.out.println("들어와따!");
		
		
		return "성공이요";
	}
	
//	/**
//	 * 등록 폼
//	 * http://localhost:9093/cosme/create.do
//	 * @param cosmeno
//	 * @return
//	 */
//	@RequestMapping(value="/cosme/create.do", method = RequestMethod.GET)
//	public ModelAndView create(int cosmeno) {
//	  ModelAndView mav = new ModelAndView();
//
//    mav.setViewName("/cosme/create"); 
//    
//    return mav;
//	  
//    
//    /**
//     * 등록 처리
//     * http://localhost:9093/cosme/create.do
//     * @return
//     */
//    @RequestMapping(value="/cosme/create.do", method=RequestMethod.POST)
//    public ModelAndView create(CosmeVO cosmeVO) {
//      
//      ModelAndView mav = new ModelAndView();
//      mav.setViewName("/cosme/msg");
//      
//      int cnt = this.cosmeProc.create(cosmeVO);
//      
//      if (cnt == 1) {
//        mav.addObject("code", "create_success");
//      } else {
//        mav.addObject("code", "create_fail");
//      }
//      
//      mav.addObject("cnt", cnt);
//      
//      return mav;
//    }
    

  
    
	  
}
