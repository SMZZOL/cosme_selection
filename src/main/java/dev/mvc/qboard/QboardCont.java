package dev.mvc.qboard;

import java.util.ArrayList;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dev.mvc.member.MemberProcInter;

@Controller
public class QboardCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.qboard.QboardProc")
  private QboardProcInter qboardProc;
  
  public QboardCont() {
    System.out.println("-> QboardCont created.");
  }
  
  /**
   * 등록폼
   * http://localhost:9093/qboard/create.do
   * @return
   */
 @RequestMapping(value="/qboard/create.do", method=RequestMethod.GET)
public ModelAndView create(HttpSession session) {
 
 ModelAndView mav = new ModelAndView();
 
 if (this.memberProc.isMember(session) == true) {
   mav.setViewName("/qboard/create");
 } else {
     mav.setViewName("/member/login_need");
   }

 
 return mav;
}
 
/**
 * 등록 처리
 * 
 * @param qboardVO
 * @return
 */
 @RequestMapping(value="/qboard/create.do", method=RequestMethod.POST)
 public ModelAndView create(QboardVO qboardVO, HttpSession session) { 
   
   ModelAndView mav = new ModelAndView();
   
   if (this.memberProc.isMember(session) == true) {
     int cnt = this.qboardProc.create(qboardVO);
       if (cnt == 1) {

         mav.setViewName("redirect:/qboard/list_all.do"); 
         
       } else {
         mav.addObject("code", "create_fail");
         mav.setViewName("/qboard/msg");

       }
       mav.addObject("cnt", cnt);
   } else {
       mav.setViewName("/member/login_need");
     }

   return mav;
 }
 
//http://localhost:9091/cate/list_all.do
@RequestMapping(value="/qboard/list_all.do", method=RequestMethod.GET)
public ModelAndView list_all() {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/qboard/list_all"); // /WEB-INF/views/cate/list_all.jsp
  
  ArrayList<QboardVO> list = this.qboardProc.list_all();
  mav.addObject("list", list);
  
  return mav;
}


}
