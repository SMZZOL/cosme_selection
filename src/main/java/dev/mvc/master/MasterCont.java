package dev.mvc.master;

import java.util.ArrayList;
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

import dev.mvc.tool.Tool;


@Controller
public class MasterCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
  
  public MasterCont() {
    System.out.println("-> MasterCont created.");
  }
  
  /**
  * 쿠키 로그인 폼
  * @return
  */
 // http://localhost:9093/master/login.do 
 @RequestMapping(value = "/master/login.do", method = RequestMethod.GET)
 public ModelAndView login_cookie(HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   
   Cookie[] cookies = request.getCookies();
   Cookie cookie = null;
 
   String ck_master_id = ""; // id 저장
   String ck_master_id_save = ""; // id 저장 여부를 체크
   String ck_master_passwd = ""; // passwd 저장
   String ck_master_passwd_save = ""; // passwd 저장 여부를 체크
 
   if (cookies != null) { // 쿠키가 존재한다면
     for (int i=0; i < cookies.length; i++){
       cookie = cookies[i]; // 쿠키 객체 추출
     
       if (cookie.getName().equals("ck_master_id")){
         ck_master_id = cookie.getValue(); 
       }else if(cookie.getName().equals("ck_master_id_save")){
         ck_master_id_save = cookie.getValue();  // Y, N
       }else if (cookie.getName().equals("ck_master_passwd")){
         ck_master_passwd = cookie.getValue();         // 1234
       }else if(cookie.getName().equals("ck_master_passwd_save")){
         ck_master_passwd_save = cookie.getValue();  // Y, N
       }
     }
   }
 
   //    <input type='text' class="form-control" name='id' id='id' 
   //            value='${ck_master_id }' required="required" 
   //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
   mav.addObject("ck_master_id", ck_master_id);
 
   //    <input type='checkbox' name='id_save' value='Y' 
   //            ${ck_master_id_save == 'Y' ? "checked='checked'" : "" }> 저장
   mav.addObject("ck_master_id_save", ck_master_id_save);
 
   mav.addObject("ck_master_passwd", ck_master_passwd);
   mav.addObject("ck_master_passwd_save", ck_master_passwd_save);
 
   mav.setViewName("/master/login_form_ck"); // /master/login_form_ck.jsp
   return mav;
 }
 
 /**
  * Cookie 기반 로그인 처리
  * @param request Cookie를 읽기위해 필요
  * @param response Cookie를 쓰기위해 필요
  * @param session 로그인 정보를 메모리에 기록
  * @param id  회원 아이디
  * @param passwd 회원 패스워드
  * @param id_save 회원 아이디 Cookie에 저장 여부
  * @param passwd_save 패스워드 Cookie에 저장 여부
  * @return
  */
  // http://localhost:9093/master/login.do 
  @RequestMapping(value = "/master/login.do",  method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(HttpServletRequest request, HttpServletResponse response, HttpSession session, MasterVO masterVO) {
    ModelAndView mav = new ModelAndView();
   
    int cnt = masterProc.login(masterVO);
    //System.out.println(masterVO.getId());
    //System.out.println(masterVO.getPasswd());

    
    if (cnt == 1) { // 로그인 성공
      MasterVO masterVO_read = masterProc.read_by_id(masterVO.getId()); // DBMS에서 id를 이용한 회원 조회
      session.setAttribute("masterno", masterVO_read.getMasterno()); // 서버의 메모리에 기록
      session.setAttribute("master_id", masterVO_read.getId());
      session.setAttribute("master_mname", masterVO_read.getMname());
      session.setAttribute("master_grade", masterVO_read.getGrade());
      String id_save = Tool.checkNull(masterVO.getId_save()); // 폼에 입력된 id 저장 여부
      String id = masterVO.getId();              // 폼에 입력된 id
      String passwd_save = Tool.checkNull(masterVO.getPasswd_save()); // 폼에 입력된 passwd 저장 여부
      String passwd = masterVO.getPasswd();              // 폼에 입력된 passwd 
      
      // -------------------------------------------------------------------
      // id 관련 쿠키 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_master_id = new Cookie("ck_master_id", id);
        ck_master_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_master_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_master_id); // id 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_master_id = new Cookie("ck_master_id", "");
        ck_master_id.setPath("/");
        ck_master_id.setMaxAge(0);
        response.addCookie(ck_master_id); // id 저장
      }
      
      // id를 저장할지 선택하는 CheckBox 체크 여부
      Cookie ck_master_id_save = new Cookie("ck_master_id_save", id_save);
      ck_master_id_save.setPath("/");
      ck_master_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_master_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠키 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_master_passwd = new Cookie("ck_master_passwd", passwd);
        ck_master_passwd.setPath("/");
        ck_master_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_master_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_master_passwd = new Cookie("ck_master_passwd", "");
        ck_master_passwd.setPath("/");
        ck_master_passwd.setMaxAge(0);
        response.addCookie(ck_master_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_master_passwd_save = new Cookie("ck_master_passwd_save", passwd_save);
      ck_master_passwd_save.setPath("/");
      ck_master_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_master_passwd_save);
      // -------------------------------------------------------------------
   
      mav.setViewName("redirect:/");  
    } else {
      mav.addObject("url", "/master/login_fail_msg");
      mav.setViewName("redirect:/master/msg.do"); 
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
    
    mav.setViewName("redirect:/"); 
    
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
  
   // http://localhost:9093/master/read.do?masterno=1
   @RequestMapping(value = "/master/read.do", method = RequestMethod.GET)
   public String read(int masterno) {
     System.out.println("-> mname: " + this.masterProc.read(masterno).getMname());
     return "";
   
   }
   
   /**
    * 모든 카테고리의 등록된 글목록, http://localhost:9093/master/list_all.do
    * @return
    */
   @RequestMapping(value="/master/list.do", method=RequestMethod.GET)
   public ModelAndView list_all(HttpSession session) {
	   ModelAndView mav = new ModelAndView();
	   
	   if (masterProc.isMaster(session)) {
		   ArrayList<MasterVO> list = this.masterProc.list();
		   mav.addObject("list", list);
     
		   mav.setViewName("/master/list"); // /webapp/WEB-INF/views/master/list.jsp
		   
	   } else {
		   mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
	      }    
		   return mav;
   }
  
}
