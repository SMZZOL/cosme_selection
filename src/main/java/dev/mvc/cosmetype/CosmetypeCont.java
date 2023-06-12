package dev.mvc.cosmetype;

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
public class CosmetypeCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
    
  @Autowired
  @Qualifier("dev.mvc.cosmetype.CosmetypeProc")
  private CosmetypeProcInter cosmetypeProc;

  public CosmetypeCont() {
    System.out.println("-> CosmetypeCont created.");
    
  }

  /**
   * 등록폼
   * http://localhost:9093/cosmetype/create.do
   * @return
   */
 @RequestMapping(value="/cosmetype/create.do", method=RequestMethod.GET)
public ModelAndView create(HttpSession session) {
 
 ModelAndView mav = new ModelAndView();
 
 if (this.masterProc.isMaster(session) == true) {
   mav.setViewName("/cosmetype/create");
 } else {
     mav.setViewName("/master/login_need");
   }

 
 return mav;
}
 
/**
 * 등록 처리
 * 
 * @param cosmetypeVO
 * @return
 */
 @RequestMapping(value="/cosmetype/create.do", method=RequestMethod.POST)
 public ModelAndView create(CosmetypeVO cosmetypeVO, HttpSession session) { 
   
   ModelAndView mav = new ModelAndView();
   
   if (this.masterProc.isMaster(session) == true) {
     int cnt = this.cosmetypeProc.create(cosmetypeVO);
       if (cnt == 1) {

         mav.setViewName("redirect:/cosmetype/list_all.do"); 
         
       } else {
         mav.addObject("code", "create_fail");
         mav.setViewName("/cosmetype/msg");

       }
       mav.addObject("cnt", cnt);
   } else {
       mav.setViewName("/master/login_need");
     }

   return mav;
 }
 
 /**
  * 목록
  * http://localhost:9093/cosmetype/list_all.do
  * @return
  */
 @RequestMapping(value="/cosmetype/list_all.do", method=RequestMethod.GET)
 public ModelAndView list_all() {
   ModelAndView mav = new ModelAndView();
   
        mav.setViewName("/cosmetype/list_all"); // /WEB-INF/views/cosme_cate/list_all.jsp
        
        ArrayList<CosmetypeVO> list = this.cosmetypeProc.list_all();
        mav.addObject("list", list);   

   return mav;
 }
 
 
 
 }
