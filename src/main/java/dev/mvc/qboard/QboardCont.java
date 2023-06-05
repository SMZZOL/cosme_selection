package dev.mvc.qboard;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cosmetype.CosmetypeVO;
import dev.mvc.master.MasterProcInter;

@Controller
public class QboardCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
  
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
 
 if (this.masterProc.isMaster(session) == true) {
   mav.setViewName("/qboard/create");
 } else {
     mav.setViewName("/master/login_need");
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
   
   if (this.masterProc.isMaster(session) == true) {
     int cnt = this.qboardProc.create(qboardVO);
       if (cnt == 1) {

         mav.setViewName("redirect:/qboard/list_all.do"); 
         
       } else {
         mav.addObject("code", "create_fail");
         mav.setViewName("/qboard/msg");

       }
       mav.addObject("cnt", cnt);
   } else {
       mav.setViewName("/master/login_need");
     }

   return mav;
 }
 
 /**
  * 질문게시판의 등록된 글목록, http://localhost:9093/qboard/list_all.do
  * @return
  */
 @RequestMapping(value="/qboard/list_all.do", method=RequestMethod.GET)
 public ModelAndView list_all() {
   ModelAndView mav = new ModelAndView();
   
   ArrayList<QboardVO> list = this.qboardProc.list_all();
   mav.addObject("list", list);
   
   mav.setViewName("/qboard/list_all"); // /webapp/WEB-INF/views/contents/list_all.jsp
   
   return mav;
 }

}
