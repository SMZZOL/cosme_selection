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
import dev.mvc.tool.Tool;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc") 
  private MasterProcInter masterProc;
  
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

    return mav;
  }
  
  /**
   * 조회
   *  http://localhost:9093/notice/read.do
   * @return
   */
  @RequestMapping(value="/notice/read.do", method=RequestMethod.GET )
  public ModelAndView read(int noticeno) {
    ModelAndView mav = new ModelAndView();

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    
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

}
