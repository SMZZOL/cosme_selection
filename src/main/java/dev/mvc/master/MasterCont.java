package dev.mvc.master;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MasterCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
  
  public MasterCont() {
    System.out.println("-> MasterCont created.");
  }
  
  /**
  * 로그인 폼
  * http://localhost:9093/master/login.do
  * @return
  */
  @RequestMapping(value="/master/login.do", method=RequestMethod.GET)
  public ModelAndView login() {
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/master/login_form"); // /WEB-INF/views/master/login_form.jsp
   
   return mav;
  }
  
  /**
  * 로그인 처리
  * http://localhost:9093/master/login.do
  * @return
  */
  @RequestMapping(value="/master/login.do", method=RequestMethod.POST)
  public ModelAndView login(HttpSession session, MasterVO masterVO) {
    ModelAndView mav = new ModelAndView();
   
    int cnt = this.masterProc.login(masterVO);
   
    if (cnt == 1) { // 로그인 성공
      MasterVO masterVO_read = this.masterProc.read_by_id(masterVO.getId()); // 관리자 정보 읽기
     
      session.setAttribute("masterno", masterVO_read.getMasterno()); // 서버의 메모리에 기록
      session.setAttribute("master_id", masterVO_read.getId());
      session.setAttribute("master_mname", masterVO_read.getMname());
      session.setAttribute("master_grade", masterVO_read.getGrade());
  
      mav.setViewName("redirect:/main.do"); // 시작 페이지
      
    } else {  // 로그인 실패
      // /WEB-INF/views/admin/login_fail_msg.jsp
      // POST 방식에서는 jsp에서 <c:import 태그가 실행이 안됨.
      // mav.setViewName("/admin/login_fail_msg");   
     
      mav.addObject("url", "/master/login_fail_msg"); // /WEB-INF/views/master/login_fail_msg.jsp
      mav.setViewName("redirect:/master/msg.do");   // POST -> url -> GET
    }
       
    return mav;    
  }
  
  /**
   * 로그아웃 처리
   * @param session
   * @return
   */
  @RequestMapping(value="/master/logout.do", method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    ModelAndView mav = new ModelAndView();
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * @return
   */
  @RequestMapping(value="/master/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  

  
}
