package dev.mvc.fboard;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.master.MasterProcInter;

import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class FboardCont {
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc") 
  private MasterProcInter masterProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.fboard.FboardProc") 
  private FboardProcInter fboardProc;
  
  public FboardCont() {
	  System.out.println("-> FboardCont created.");
  }
  
  // 등록 폼, notice 테이블은 FK로 fboardno를 사용함.
  // http://localhost:9093/fboard/create.do
  @RequestMapping(value="/fboard/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.memberProc.isMember(session) == true) {
    	
    	mav.setViewName("/fboard/create"); // /webapp/WEB-INF/views/fboard/create.jsp
    	
    } else {
        mav.setViewName("/member/login_need"); // /WEB-INF/views/member/login_need.jsp
      }    
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/fboard/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/fboard/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, FboardVO fboardVO) {
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.isMember(session)) {  // 회원으로 로그인한 경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // preview image

      String upDir =  Fboard.getUploadDir();
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = fboardVO.getFile1MF();
      
      file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
      System.out.println("-> file1: " + file1);
      
      long size1 = mf.getSize();  // 파일 크기
      
      if (size1 > 0) { // 파일 크기 체크
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1saved)) { // 이미지인지 검사
          // thumb 이미지 생성 후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
        }
        
      }    
      
      fboardVO.setFile1(file1);   // 순수 원본 파일명
      fboardVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      fboardVO.setThumb1(thumb1);      // 원본 이미지 축소판
      fboardVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int memberno = (int)session.getAttribute("memberno"); // memberno FK
      fboardVO.setMemberno(memberno);
      int cnt = this.fboardProc.create(fboardVO); 
      
      // ------------------------------------------------------------------------------
      // PK의 return
      // ------------------------------------------------------------------------------
      // System.out.println("--> fboardno: " + fboardVO.getFboardno());
      // mav.addObject("fboardno", fboardVO.getFboardno()); // redirect parameter 적용
      // ------------------------------------------------------------------------------
      
      if (cnt == 1) {

      	//mav.addObject("code", "create_success");
      	mav.setViewName("redirect:/fboard/list_all.do");     // 목록으로 자동 이동
                  
      } else {
        mav.addObject("code", "create_fail");
        
      }
      mav.addObject("cnt", cnt);

      
  } else {
      mav.setViewName("/member/login_need");
       
    }
    
    return mav; // forward
  }
  
  /**
   * 모든 카테고리의 등록된 글목록, http://localhost:9093/fboard/list_all.do
   * @return
   */
  @RequestMapping(value="/fboard/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<FboardVO> list = this.fboardProc.list_all();
    mav.addObject("list", list);
    
    mav.setViewName("/fboard/list_all"); // /webapp/WEB-INF/views/fboard/list_all.jsp
    
    return mav;
  }
  

}
