package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.master.*;
import dev.mvc.cosme_cate.*;
import dev.mvc.ingred.*;

@Controller
public class CosmeCont {
  @Autowired
  @Qualifier("dev.mvc.cosme.CosmeProc")
  private CosmeProcInter cosmeProc;
  
  @Autowired
  @Qualifier("dev.mvc.cosme_cate.Cosme_cateProc")
  private Cosme_cateProcInter cosme_cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.master.MasterProc")
  private MasterProcInter masterProc;
  
  public CosmeCont() {
    System.out.println("-> CosmeCont created.");
  }
	
	// http://localhost:9093/cosme/list_by_type.do 404
	//	@PostMapping("/cosme/list_by_type.do")
	@ResponseBody
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.POST)
	public String listByTypePost(@RequestBody Map<String, Object> request) {

		ArrayList<String> list= (ArrayList<String>) request.get("list");
		if(list.isEmpty()) {
			System.out.println("empty");
		}
		for(String num : list) {
			System.out.print(num + ", ");
		}
//		System.out.println(request);
//		System.out.println("들어와따!");
		
		
		return "성공이요";
	}
	
//	/**
//	 * 등록 폼
//	 * http://localhost:9093/cosme/create.do
//	 * @param cosmeno
//	 * @return
//	 */
	  @RequestMapping(value="/cosme/create.do", method = RequestMethod.GET)
	  public ModelAndView create() {
  	  ModelAndView mav = new ModelAndView();
     
  	  ArrayList<Cosme_cateVO> list2 = this.cosme_cateProc.list_all(); // 카테고리 목록 가져오기
  	  
  	  for (Cosme_cateVO item : list2) {
//  	    System.out.println("화장품 종류 이름: " + item.getCosme_catename());
  	  }

  	  mav.addObject("list2", list2); // 모델에 카테고리 목록 추가
      mav.setViewName("/cosme/create"); // create.jsp
      
      return mav;
	  }
 
//    /**
//     * 등록 처리
//     * http://localhost:9093/cosme/create.do
//     * @return
//     */
//    @RequestMapping(value="/cosme/create.do", method=RequestMethod.POST)
//    public ModelAndView create(CosmeVO cosmeVO, HttpSession session) {
//
//      ModelAndView mav = new ModelAndView();
//      mav.setViewName("/cosme/msg");
// 
//      if (this.masterProc.isMaster(session) == true) {
//        int cnt = this.cosmeProc.create(cosmeVO);
//          if (cnt == 1) {
//            // request.setAttribute("code", "create_success"); // 고전적인 jsp 방법 
//            // mav.addObject("code", "create_success");
//            mav.setViewName("redirect:/cosme/list_all.do");     // 목록으로 자동 이동
//            
//          } else {
//            // request.setAttribute("code", "create_fail");
//            mav.addObject("code", "create_fail");
//            mav.setViewName("/cosme/msg"); // /WEB-INF/views/cate/msg.jsp // 등록 실패 메시지 출력
//
//          }
//          
//          // request.setAttribute("cnt", cnt);
//          mav.addObject("cnt", cnt);
//      } else {
//          mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
//        }
//      
//      return mav;
//    }
    
//    @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
	 @RequestMapping(value="/cosme/create.do", method=RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request, HttpSession session, CosmeVO cosmeVO) {
      ModelAndView mav = new ModelAndView();
      
      if (masterProc.isMaster(session)) { // 관리자로 로그인한경우
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image
        String file1saved = "";   // 저장된 파일명, image
        String thumb1 = "";     // preview image

        String upDir =  Cosme.getUploadDir();
        System.out.println("-> upDir: " + upDir);
        
        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = cosmeVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
        System.out.println("-> file1: " + file1);
        
        long size1 = mf.getSize();  // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
          }
          
        }    
        
        cosmeVO.setCosme_file(file1);   // 순수 원본 파일명
        cosmeVO.setCosme_file_saved(file1saved); // 저장된 파일명(파일명 중복 처리)
        cosmeVO.setCosme_file_preview(thumb1);      // 원본이미지 축소판
        cosmeVO.setCosme_file_size(size1);  // 파일 크기
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 종료
        // ------------------------------------------------------------------------------
        

        // 다른 필요한 핸들러 메서드를 추가로 구현할 수 있습니다.

        // Call By Reference: 메모리 공유, Hashcode 전달
        int masterno = (int)session.getAttribute("masterno"); // adminno FK
        cosmeVO.setMasterno(masterno);
        int cnt = this.cosmeProc.create(cosmeVO); 

        // ------------------------------------------------------------------------------
        // PK의 return
        // ------------------------------------------------------------------------------
        if (cnt == 1) {
         // this.cosmeProc.update_cnt_add(cosmeVO.getCosmeno()); 
          mav.addObject("code", "create_success");
        } else {
          mav.addObject("code", "create_fail");
        }
        mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
        
        // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
        mav.addObject("cosmeno", cosmeVO.getCosmeno()); // redirect parameter 적용
        
        mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/contents/msg.do"); 

      } else {
        mav.addObject("url", "/master/login_need"); // /WEB-INF/views/master/login_need.jsp
        mav.setViewName("redirect:/contents/msg.do"); 
      }
      
      return mav; // forward
    }
    
//    /**
//     * 화장품 등록 창에서 카테고리 목록 표시
//     * http://localhost:9093/cosme/create.do
//     * @return
//     */
//    // http://localhost:9093/cosme/create.do
//    @RequestMapping(value="/cosme/create.do", method = RequestMethod.POST)
//    public ModelAndView cate_all() {
//        ModelAndView mav = new ModelAndView();
//        
//        ArrayList<CosmeVO> list2 = this.cosmeProc.cate_all(); // 카테고리 목록 가져오기
//        mav.addObject("list2", list2); // 모델에 카테고리 목록 추가
//        
//        mav.setViewName("/cosme/create"); 
//        
//        return mav;
//    }
    
//  /**
//  * 수정 폼
//  * http://localhost:9093/cosme/update.do
//  * @param cosmeno
//  * @return
//  */
   @RequestMapping(value="/cosme/update.do", method = RequestMethod.GET)
   public ModelAndView update_all_cosme() {
   ModelAndView mav = new ModelAndView();

   mav.setViewName("/cosme/update"); 
   
   return mav;
   }

//   /**
//    * 수정 처리
//    * http://localhost:9093/cosme/update.do
//    * @return
//    */
   @RequestMapping(value="/cosme/update.do", method=RequestMethod.POST)
   public ModelAndView update_all_cosme(CosmeVO cosmeVO) {

     ModelAndView mav = new ModelAndView();
     mav.setViewName("/cosme/msg");
 
     int cnt = this.cosmeProc.update_all_cosme(cosmeVO);

     if (cnt == 1) {
       mav.addObject("code", "update_success");
       } else {
         mav.addObject("code", "update_fail");
       }

       mav.addObject("cnt", cnt);

       return mav;
   }
    
    
    /**
     * 모든 카테고리의 등록된 글목록, http://localhost:9093/cosme/list_by_type.do
     * @return
     */
    @RequestMapping(value="/cosme/list_by_type.do", method=RequestMethod.GET)
    public ModelAndView cosme_all() {
      ModelAndView mav = new ModelAndView();
      
      ArrayList<CosmeVO> list = this.cosmeProc.cosme_all();
      mav.addObject("list", list);
      
      mav.setViewName("/cosme/list_by_type"); // /webapp/WEB-INF/views/cosme/list_by_type.jsp
      
      return mav;
    }
    

}