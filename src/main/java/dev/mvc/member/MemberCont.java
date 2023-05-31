package dev.mvc.member;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  public MemberCont() {
    System.out.println("-> MemberCont created. ");
  }
  
  /**http://localhost9093/member/checkID.do?id=user1
   * ID 중복 체크, JSON 출력
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/member/checkID.do", method=RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
  public String checkID(String id) {
    int cnt = this.memberProc.checkID(id);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString();
  }
  
  /**http://localhost9093/member/create.do
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create");
    
    return mav;
  }
  
  /**
   * 등록 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    
    memberVO.setGrade(15); // 기본 회원 가입 등록 15 지정
    
    int cnt= memberProc.create(memberVO); // SQL insert
    
    if (cnt == 1) { // insert 레코드 개수
      mav.addObject("code", "create_success");
      mav.addObject("mname", memberVO.getMname());  // 홍길동님(user4) 회원 가입을 축하합니다.
      mav.addObject("id", memberVO.getId());
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do"); // POST -> GET -> /member/msg.jsp

//    mav.addObject("code", "create_fail"); // 가입 실패 test용
//    mav.addObject("cnt", 0);                 // 가입 실패 test용
    
    return mav;
  }
  
  /**
   * 새로고침 방지, EL에서 param으로 접근, POST -> GET -> /member/msg.jsp
   * @return
   */
  @RequestMapping(value="/member/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
  * 목록 출력 가능
  * @param session
  * @return
  */
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      ArrayList<MemberVO> list = this.memberProc.list();
      mav.addObject("list", list);

      mav.setViewName("/member/list"); // /webapp/WEB-INF/views/member/list.jsp

    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
        
    return mav;
  } 
  
  

}
