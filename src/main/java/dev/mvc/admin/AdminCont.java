package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;

@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  public AdminCont() {
    System.out.println("-> AdminCont created.");
  }
  
  
//http://localhost:9093/admin/checkID.do?id=user1@gmail.com
 /**
 * ID 중복 체크, JSON 출력
 * @return
 */
 @ResponseBody
 @RequestMapping(value="/admin/checkID.do", method=RequestMethod.GET ,
                        produces = "text/plain;charset=UTF-8" )
 public String checkID(String id) {
   int cnt = this.adminProc.checkID(id);
  
   JSONObject json = new JSONObject();
   json.put("cnt", cnt); 
  
   return json.toString(); // {"cnt":1} 
 }
  
//http://localhost:9093/admin/create.do
 /**
 * 등록 폼
 * @return
 */
 @RequestMapping(value="/admin/create.do", method=RequestMethod.GET )
 public ModelAndView create() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/create"); // /WEB-INF/views/admin/create.jsp
  
   return mav; // forward
 }

 /**
  * 등록 처리
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/create.do", method=RequestMethod.POST)
 public ModelAndView create(AdminVO adminVO){
   ModelAndView mav = new ModelAndView();
   
   // System.out.println("id: " + memberVO.getId());
   
   adminVO.setGrade(15); // 기본 회원 가입 등록 15 지정
   
   int cnt= this.adminProc.create(adminVO); // SQL insert
   
   if (cnt == 1) { // insert 레코드 개수
     mav.addObject("code", "create_success");
     mav.addObject("mname", adminVO.getMname());  // 홍길동님(user4) 회원 가입을 축하합니다.
     mav.addObject("id", adminVO.getId());
   } else {
     mav.addObject("code", "create_fail");
   }
   
   mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
   
   mav.addObject("url", "/admin/msg");  // /member/msg -> /admin/msg.jsp
   
   mav.setViewName("redirect:/admin/msg.do"); // POST -> GET -> /admin/msg.jsp

//   mav.addObject("code", "create_fail"); // 가입 실패 test용
//   mav.addObject("cnt", 0);                 // 가입 실패 test용
   
   return mav;
 }
  
  
//  /**
//   * 로그인 폼
//   * http://localhost:9091/admin/login.do
//   * @return
//   */
//  @RequestMapping(value="/admin/login.do", method=RequestMethod.GET)
//  public ModelAndView login() {
//    ModelAndView mav = new ModelAndView();
//    
//    mav.setViewName("/admin/login_form"); // /WEB-INF/views/admin/login_form.jsp
//    
//    return mav;
//  }
//
//  /**
//   * 로그인 처리
//   * http://localhost:9091/admin/login.do
//   * @return
//   */
//  @RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
//  public ModelAndView login(HttpSession session,
//                                       AdminVO adminVO) {
//    ModelAndView mav = new ModelAndView();
//    
//    int cnt = this.adminProc.login(adminVO);
//    
//    if (cnt == 1) { // 로그인 성공
//      AdminVO adminVO_read = this.adminProc.read_by_id(adminVO.getId()); // 관리자 정보 읽기
//      
//      session.setAttribute("adminno", adminVO_read.getAdminno()); // 서버의 메모리에 기록
//      session.setAttribute("admin_id", adminVO_read.getId());
//      session.setAttribute("admin_mname", adminVO_read.getMname());
//      session.setAttribute("admin_grade", adminVO_read.getGrade());
//
//      mav.setViewName("redirect://"); // 시작 페이지
//    } else {  // 로그인 실패
//      // /WEB-INF/views/admin/login_fail_msg.jsp
//      // POST 방식에서는 jsp에서 <c:import 태그가 실행이 안됨.
//      // mav.setViewName("/admin/login_fail_msg");   
//      
//      mav.addObject("url", "/admin/login_fail_msg"); // /WEB-INF/views/admin/login_fail_msg.jsp
//      mav.setViewName("redirect:/admin/msg.do");   // POST -> url -> GET
//    }
//        
//    return mav;
//  }
  
  /**
   * 로그아웃 처리
   * @param session
   * @return
   */
  @RequestMapping(value="/admin/logout.do", method=RequestMethod.GET)
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
  @RequestMapping(value="/admin/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 로그인 폼
   * @return
   */
  // http://localhost:9091/admin/login.do 
  @RequestMapping(value = "/admin/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_admin_id = ""; // id 저장
    String ck_admin_id_save = ""; // id 저장 여부를 체크
    String ck_admin_passwd = ""; // passwd 저장
    String ck_admin_passwd_save = ""; // passwd 저장 여부를 체크
  
    if (cookies != null) { // 쿠키가 존재한다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
      
        if (cookie.getName().equals("ck_admin_id")){
          ck_admin_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_admin_id_save")){
          ck_admin_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_admin_passwd")){
          ck_admin_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_admin_passwd_save")){
          ck_admin_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
  
    //    <input type='text' class="form-control" name='id' id='id' 
    //            value='${ck_admin_id }' required="required" 
    //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
    mav.addObject("ck_admin_id", ck_admin_id);
  
    //    <input type='checkbox' name='id_save' value='Y' 
    //            ${ck_admin_id_save == 'Y' ? "checked='checked'" : "" }> 저장
    mav.addObject("ck_admin_id_save", ck_admin_id_save);
  
    mav.addObject("ck_admin_passwd", ck_admin_passwd);
    mav.addObject("ck_admin_passwd_save", ck_admin_passwd_save);
  
    mav.setViewName("/admin/login_form_ck"); // /admin/login_form_ck.jsp
    return mav;
  }
   
//  /**
//   * Cookie 로그인 폼
//   * @return
//   */
//  // http://localhost:9091/admin/login.do 
//  @RequestMapping(value = "/admin/login.do", 
//                             method = RequestMethod.GET)
//  public ModelAndView login_cookie(HttpServletRequest request) {
//    ModelAndView mav = new ModelAndView();
//  
//    mav.setViewName("/admin/login_form_ck"); // /admin/login_form_ck.jsp
//    
//    return mav;
//  }
  
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
  // http://localhost:9091/admin/login.do 
  @RequestMapping(value = "/admin/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            AdminVO adminVO) {
    ModelAndView mav = new ModelAndView();
   
    int cnt = adminProc.login(adminVO);
    if (cnt == 1) { // 로그인 성공
      AdminVO adminVO_read = adminProc.read_by_id(adminVO.getId()); // DBMS에서 id를 이용한 회원 조회
      session.setAttribute("adminno", adminVO_read.getAdminno()); // 서버의 메모리에 기록
      session.setAttribute("admin_id", adminVO_read.getId());
      session.setAttribute("admin_mname", adminVO_read.getMname());
      session.setAttribute("admin_grade", adminVO_read.getGrade());
   
      String id_save = Tool.checkNull(adminVO.getId_save()); // 폼에 입력된 id 저장 여부
      String id = adminVO.getId();              // 폼에 입력된 id
      String passwd_save = Tool.checkNull(adminVO.getPasswd_save()); // 폼에 입력된 passwd 저장 여부
      String passwd = adminVO.getPasswd();              // 폼에 입력된 passwd 
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", id);
        ck_admin_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_admin_id); // id 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", "");
        ck_admin_id.setPath("/");
        ck_admin_id.setMaxAge(0);
        response.addCookie(ck_admin_id); // id 저장
      }
      
      // id를 저장할지 선택하는 CheckBox 체크 여부
      Cookie ck_admin_id_save = new Cookie("ck_admin_id_save", id_save);
      ck_admin_id_save.setPath("/");
      ck_admin_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", passwd);
        ck_admin_passwd.setPath("/");
        ck_admin_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_admin_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", "");
        ck_admin_passwd.setPath("/");
        ck_admin_passwd.setMaxAge(0);
        response.addCookie(ck_admin_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_admin_passwd_save = new Cookie("ck_admin_passwd_save", passwd_save);
      ck_admin_passwd_save.setPath("/");
      ck_admin_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_passwd_save);
      // -------------------------------------------------------------------
   
      mav.setViewName("redirect:/");  
    } else {
      mav.addObject("url", "/admin/login_fail_msg");
      mav.setViewName("redirect:/admin/msg.do"); 
    }
       
    return mav;
  }
  
  // http://localhost:9093/admin/read.do?adminno=1
  @RequestMapping(value = "/admin/read.do", method = RequestMethod.GET)
  public ModelAndView read(HttpSession session, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    
    int adminno= 0;
    if (this.adminProc.isAdmin(session)) { 
      // 로그인한 경우

      if (this.adminProc.isAdmin(session)) { // 관리자로 로그인
        adminno = Integer.parseInt(request.getParameter("adminno")); // 관리자는 누구나 조회 가능
        
        AdminVO adminVO = this.adminProc.read(adminno);
        mav.addObject("adminVO", adminVO);
        mav.setViewName("/admin/read"); // /member/read.jsp
        
        
      } 
     
    } else {
      // 로그인을 하지 않은 경우
      mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp
    }
    
    return mav; // forward
  
  }
  
  /**
   * 목록 출력 가능
   * @param session
   * @return
   */
   @RequestMapping(value="/admin/list.do", method=RequestMethod.GET)
   public ModelAndView list(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (this.adminProc.isAdmin(session) == true) {
       ArrayList<AdminVO> list = this.adminProc.list();
       mav.addObject("list", list);

       mav.setViewName("/admin/list"); // /webapp/WEB-INF/views/member/list.jsp

     } else {
       mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
     }
         
     return mav;
   }  
   

   
   /**
    * 관리자 수정 처리
    * @param adminVO
    * @return
    */
   @RequestMapping(value="/admin/update.do", method=RequestMethod.POST)
   public ModelAndView update(AdminVO adminVO){
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("id: " + memberVO.getId());
     
     int cnt= this.adminProc.update(adminVO);
     
     if (cnt == 1) {
       mav.addObject("code", "update_success");
       mav.addObject("mname", adminVO.getMname());  // 홍길동님(user4) 회원 정보를 변경했습니다.
       mav.addObject("id", adminVO.getId());
     } else {
       mav.addObject("code", "update_fail");
     }

     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("url", "/admin/msg");  // /member/msg -> /member/msg.jsp
     
     mav.setViewName("redirect:/admin/msg.do");
     
     return mav;
   }
   
   /**
    * 회원 삭제
    * @param adminno
    * @return
    */
   @RequestMapping(value="/admin/delete.do", method=RequestMethod.GET)
   public ModelAndView delete(int adminno){
     ModelAndView mav = new ModelAndView();
     
     AdminVO adminVO = this.adminProc.read(adminno); // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
     mav.addObject("adminVO", adminVO);
     mav.setViewName("/admin/delete"); // /admin/delete.jsp
     
     return mav; // forward
   }
   
   /**
    * 회원 삭제 처리
    * @param adminVO
    * @return
    */
   @RequestMapping(value="/admin/delete.do", method=RequestMethod.POST)
   public ModelAndView delete_proc(int adminno){
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("id: " + memberVO.getId());
     // 삭제된 정보를 msg.jsp에 출력하기 위해, 삭제전에 회원 정보를 읽음.
     AdminVO adminVO = this.adminProc.read(adminno); 
     
     int cnt= this.adminProc.delete(adminno);

     if (cnt == 1) {
       mav.addObject("code", "delete_success");
       mav.addObject("mname", adminVO.getMname());  // 홍길동님(user4) 회원 정보를 변경했습니다.
       mav.addObject("id", adminVO.getId());
     } else {
       mav.addObject("code", "delete_fail");
     }

     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("url", "/admin/msg");  // /member/msg -> /member/msg.jsp
     
     mav.setViewName("redirect:/admin/msg.do");
     
     return mav;
   }
   
   /**
    * 관리자 패스워드를 변경합니다.
    * @param adminno
    * @return
    */
   @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.GET)
   public ModelAndView passwd_update(int adminno){
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/admin/passwd_update"); // passwd_update.jsp
     
     return mav;
   }
   
   /**
    * 관리자 패스워드 변경 처리
    * @param adminno 회원 번호
    * @param current_passwd 현재 패스워드
    * @param new_passwd 새로운 패스워드
    * @return
    */
   @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.POST)
   public ModelAndView passwd_update(int adminno, String current_passwd, String new_passwd){
     ModelAndView mav = new ModelAndView();
     
     AdminVO adminVO = this.adminProc.read(adminno); // 패스워드를 변경하려는 회원 정보를 읽음
     mav.addObject("mname", adminVO.getMname());  
     mav.addObject("id", adminVO.getId());
     
     // 현재 패스워드 검사용 데이터
     HashMap<Object, Object> map = new HashMap<Object, Object>();
     map.put("adminno", adminno);
     map.put("passwd", current_passwd);
     
     int cnt = adminProc.passwd_check(map); // 현재 패스워드 검사
     int update_cnt = 0; // 변경된 패스워드 수
     
     if (cnt == 1) { // 현재 패스워드가 일치하는 경우
       map.put("passwd", new_passwd); // 새로운 패스워드를 저장
       update_cnt = this.adminProc.passwd_update(map); // 패스워드 변경 처리
       
       if (update_cnt == 1) {
         mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
       } else {
         cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
         mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
       }
       
       mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
     } else {
       mav.addObject("code", "passwd_fail"); // 패스워드가 일치하지 않는 경우
     }

     mav.addObject("cnt", cnt); // 패스워드 일치 여부
     mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
     
     mav.setViewName("redirect:/admin/msg.do");
     
     return mav;
   }
  
}


