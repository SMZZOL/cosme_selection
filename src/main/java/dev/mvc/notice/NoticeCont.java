package dev.mvc.notice;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cosme_cate.Cosme_cateVO;
import dev.mvc.master.MasterProcInter;
import dev.mvc.master.MasterVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc") 
  private MasterProcInter masterProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc") 
  private NoticeProcInter noticeProc;
  
  public NoticeCont () {
    System.out.println("-> NoticeCont created.");
  		}
  
  // 등록 폼, notice 테이블은 FK로 noticeno를 사용함.
  // http://localhost:9093/notice/create.do
  @RequestMapping(value="/notice/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
 
    
    if (this.masterProc.isMaster(session) == true) {
    	
    	mav.setViewName("/notice/create"); // /webapp/WEB-INF/views/notice/create.jsp
    	
    } else {
        mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
      }    
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
    if (masterProc.isMaster(session)) { // 관리자로 로그인한 경우
        
        // Call By Reference: 메모리 공유, Hashcode 전달
        int masterno = (int)session.getAttribute("masterno"); // masterno FK
        noticeVO.setMasterno(masterno);
        int cnt = this.noticeProc.create(noticeVO);
        
        // ------------------------------------------------------------------------------
        // PK의 return
        // ------------------------------------------------------------------------------
        // System.out.println("--> noticeno: " + noticeVO.getNoticeno());
        // mav.addObject("noticeno", noticeVO.getNoticeno()); // redirect parameter 적용
        // ------------------------------------------------------------------------------
        
        if (cnt == 1) {

        	//mav.addObject("code", "create_success");
        	mav.setViewName("redirect:/notice/list_all.do");     // 목록으로 자동 이동
                    
        } else {
          mav.addObject("code", "create_fail");
          
        }
        mav.addObject("cnt", cnt);
 
        
    } else {
        mav.setViewName("/master/login_need");
        mav.setViewName("redirect:/notice/msg.do"); 
      }
    
    return mav;

	}
  
  /**
   * 목록
   * http://localhost:9093/notice/list.all.do
   * @return
   */
  @RequestMapping(value="/notice/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/notice/list_all"); // /WEB-INF/views/notice/list_all.jsp
  
    ArrayList<NoticeVO> list = this.noticeProc.list_all();
    mav.addObject("list", list); 
    
    mav.setViewName("/notice/list_all"); // /webapp/WEB-INF/views/notice/list_all.jsp

    return mav;
  }
  
  /**
   * 조회
   *  http://localhost:9093/notice/read.do
   * @return
   */
  @RequestMapping(value="/notice/read.do", method=RequestMethod.GET )
  public ModelAndView read(int noticeno, HttpSession session) {
    ModelAndView mav = new ModelAndView();

    
    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    
    String title = noticeVO.getNtitle();
    String content = noticeVO.getNcontent();
    
    title = Tool.convertChar(title);  // 특수 문자 처리
    content = Tool.convertChar(content); 
    
    noticeVO.setNtitle(title);
    noticeVO.setNcontent(content);  
    
    mav.addObject("noticeVO", noticeVO); // request.setAttribute("noticeVO", noticeVO);

    // 관리자 번호: masterno -> MasterVO -> mname
    String mname = this.masterProc.read(noticeVO.getMasterno()).getMname();
    mav.addObject("mname", mname);

    mav.setViewName("/notice/read"); // /WEB-INF/views/notice/read.jsp
    
    return mav;
  }
  
  /**
   * 패스워드 일치 검사
   * http://localhost:9093/notice/password_check.do?noticeno=1&passwd=1234
   * @return
   */
  @RequestMapping(value="/notice/password_check.do", method=RequestMethod.GET )
  public ModelAndView password_check(NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.noticeProc.password_check(noticeVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 0) {
      mav.setViewName("/notice/passwd_check"); // /WEB-INF/views/notice/passwd_check.jsp
    }
        
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/notice/update.do?noticeno=1
   * 
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int noticeno) {
    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);   
    
    mav.setViewName("/notice/update"); // /WEB-INF/views/notice/update.jsp

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/notice/update.do?noticeno=1
   * 
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpSession session, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    //System.out.println("title: " + noticeVO.getNtitle());   
    
    if (this.masterProc.isMaster(session)) { // 관리자 로그인
      this.noticeProc.update(noticeVO);  
      
      mav.addObject("noticeno", noticeVO.getNoticeno());
      mav.setViewName("redirect:/notice/read.do");
      
    } else { // 정상적인 로그인이 아닌 경우
      if (this.noticeProc.password_check(noticeVO) == 1) {
        this.noticeProc.update(noticeVO);  
         
        // mav 객체 이용
        mav.addObject("noticeno", noticeVO.getNoticeno());
        mav.setViewName("redirect:/notice/read.do");
        
      } else {
        mav.addObject("url", "/notice/passwd_check"); // /WEB-INF/views/notice/passwd_check.jsp
        mav.setViewName("redirect:/notice/list_all.do");  // POST -> GET -> JSP 출력
      }    
    }
    
    
    return mav; // forward
  }

}
