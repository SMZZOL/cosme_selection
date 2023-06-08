package dev.mvc.cosme_cate;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.master.MasterProcInter;


@Controller
public class Cosme_cateCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
	  
  @Autowired
  @Qualifier("dev.mvc.cosme_cate.Cosme_cateProc")
  private Cosme_cateProcInter cosme_cateProc;

  

  /**
   * 등록폼
   * http://localhost:9093/cosme_cate/create.do
   * @return
   */
 @RequestMapping(value="/cosme_cate/create.do", method=RequestMethod.GET)
public ModelAndView create(HttpSession session) {
 
 ModelAndView mav = new ModelAndView();
 
 if (this.masterProc.isMaster(session) == true) {
	 mav.setViewName("/cosme_cate/create");
 } else {
     mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
   }

 
 return mav;
}
 
/**
 * 등록 처리
 * 
 * @param cosme_cateVO
 * @return
 */
 @RequestMapping(value="/cosme_cate/create.do", method=RequestMethod.POST)
 public ModelAndView create(Cosme_cateVO cosme_cateVO, HttpSession session) { 
   
   ModelAndView mav = new ModelAndView();
   
   if (this.masterProc.isMaster(session) == true) {
	   int cnt = this.cosme_cateProc.create(cosme_cateVO);
	     if (cnt == 1) {
	       // request.setAttribute("code", "create_success"); // 고전적인 jsp 방법 
	       // mav.addObject("code", "create_success");
	       mav.setViewName("redirect:/cosme_cate/list_all.do");     // 목록으로 자동 이동
	       
	     } else {
	       // request.setAttribute("code", "create_fail");
	       mav.addObject("code", "create_fail");
	       mav.setViewName("/cosme_cate/msg"); // /WEB-INF/views/cate/msg.jsp // 등록 실패 메시지 출력

	     }
	     
	     // request.setAttribute("cnt", cnt);
	     mav.addObject("cnt", cnt);
   } else {
	     mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
	   }

   
   return mav;
 }
 
 /**
  * 목록
  * http://localhost:9093/cosme_cate/list.all
  * @return
  */
 // http://localhost:9093/cosme_cate/list_all.do
 @RequestMapping(value="/cosme_cate/list_all.do", method=RequestMethod.GET)
 public ModelAndView list_all() {
   ModelAndView mav = new ModelAndView();
   
	      mav.setViewName("/cosme_cate/list_all"); // /WEB-INF/views/cosme_cate/list_all.jsp
	      
	      ArrayList<Cosme_cateVO> list = this.cosme_cateProc.list_all();
	      mav.addObject("list", list);   

   return mav;
 }
 
 /**
  * 종류별 리스트
  * http://localhost:9093/cosme_cate/list_by_cate.do
  * @return
  */
 // http://localhost:9093/cosme_cate/list_all.do
 @RequestMapping(value="/cosme_cate/list_by_cate.do", method=RequestMethod.GET)
 public ModelAndView list_by_cate() {
   ModelAndView mav = new ModelAndView();
   
        mav.setViewName("/cosme_cate/list_by_cate"); // /WEB-INF/views/cosme_cate/list_all.jsp
        
        ArrayList<Cosme_cateVO> list = this.cosme_cateProc.list_by_cate();
        mav.addObject("list", list);   

   return mav;
 }
 }
