package dev.mvc.qboard;

import java.util.ArrayList;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dev.mvc.master.MasterProcInter;
import dev.mvc.master.MasterVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.notice.NoticeVO;
import dev.mvc.tool.Tool;

@Controller
public class QboardCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
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
 /**
  * 목록
  * @return
  */
//http://localhost:9093/qboard/list_all.do
@RequestMapping(value="/qboard/list_all.do", method=RequestMethod.GET)
public ModelAndView list_all() {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/qboard/list_all"); // /WEB-INF/views/cate/list_all.jsp
  
  ArrayList<QboardVO> list = this.qboardProc.list_all();
  mav.addObject("list", list);
  
  return mav;
}

// http://localhost:9091/contents/read.do
/**
 * 조회
 * @return
 */
@RequestMapping(value="/qboard/read.do", method=RequestMethod.GET )
public ModelAndView read(int qboardno) {
  ModelAndView mav = new ModelAndView();

  QboardVO qboardVO = this.qboardProc.read(qboardno);
  
  String qtitle = qboardVO.getQtitle();
  String qcontent = qboardVO.getQcontent();
  
  qtitle = Tool.convertChar(qtitle);  // 특수 문자 처리
  qcontent = Tool.convertChar(qcontent); 
  
  qboardVO.setQtitle(qtitle);
  qboardVO.setQcontent(qcontent);   
  
  mav.addObject("qboardVO", qboardVO); // request.setAttribute("contentsVO", contentsVO);

  mav.setViewName("/qboard/read"); // /WEB-INF/views/contents/read.jsp
      
  return mav;
}

/**
 * 삭제 폼
 * @param qboardno
 * @return
 */
@RequestMapping(value="/qboard/delete.do", method=RequestMethod.GET )
public ModelAndView delete(HttpSession session, int qboardno) { 
  ModelAndView mav = new  ModelAndView();
  
  if (this.masterProc.isMaster(session)) { // 관리자 로그인
  // 삭제할 정보를 조회하여 확인
  QboardVO qboardVO = this.qboardProc.read(qboardno);
  mav.addObject("qboardVO", qboardVO);
  
  mav.setViewName("/qboard/delete");  // /webapp/WEB-INF/views/notice/delete.jsp
  
  }else {
    mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
  }
  
  return mav; 
}

/**
 * 삭제 처리 http://localhost:9093/qboard/delete.do
 * 
 * @return
 */
@RequestMapping(value = "/qboard/delete.do", method = RequestMethod.POST)
public ModelAndView delete(HttpSession session, QboardVO qboardVO) {
  ModelAndView mav = new ModelAndView();
  
  //NoticeVO noticeVO_read = this.noticeProc.read(noticeVO.getNoticeno()); 

  this.qboardProc.delete(qboardVO.getQboardno()); // DBMS 삭제
  
  if (this.masterProc.isMaster(session)) { // 관리자 로그인
  mav.setViewName("redirect:/qboard/list_all.do");
  
  }else {
    mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
  }
  
  return mav;
}   




}
