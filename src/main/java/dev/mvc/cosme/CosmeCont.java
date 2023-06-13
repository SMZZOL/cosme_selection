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
	  public ModelAndView create(HttpSession session) {
  	  ModelAndView mav = new ModelAndView();
     
  	  ArrayList<Cosme_cateVO> list2 = this.cosme_cateProc.list_all(); // 카테고리 목록 가져오기
  	  
  	  for (Cosme_cateVO item : list2) {
//  	    System.out.println("화장품 종류 이름: " + item.getCosme_catename());
  	  }

  	  mav.addObject("list2", list2); // 모델에 카테고리 목록 추가
      mav.setViewName("/cosme/create"); // create.jsp
      
      if (this.masterProc.isMaster(session) == true) {
        mav.setViewName("/cosme/create");
      } else {
          mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
        }
      
      return mav;
	  }
    
//    @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
	 @RequestMapping(value="/cosme/create.do", method=RequestMethod.POST)
    public ModelAndView create(CosmeVO cosmeVO, HttpServletRequest request, HttpSession session) {
      ModelAndView mav = new ModelAndView();
      
      if (masterProc.isMaster(session) == true) { // 관리자로 로그인한경우
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
          mav.setViewName("/cosme/msg");
        } else {
          mav.addObject("code", "create_fail");
          mav.setViewName("/cosme/msg");
        }
        mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
        
        // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
        mav.addObject("cosmeno", cosmeVO.getCosmeno()); // redirect parameter 적용
        
        mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/cosme/msg.do"); 

      } else {
        mav.addObject("url", "/master/login_need"); // /WEB-INF/views/master/login_need.jsp
        mav.setViewName("redirect:/cosme/msg.do"); 
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
//  * http://localhost:9093/cosme/update.do?cosmeno=1
//  * @param cosmeno
//  * @return
//  */
   @RequestMapping(value="/cosme/update.do", method = RequestMethod.GET)
   public ModelAndView update_all_cosme(int cosmeno) {
     ModelAndView mav = new ModelAndView();
     
     CosmeVO cosmeVO = this.cosmeProc.cosme_read(cosmeno);
     mav.addObject("cosmeVO", cosmeVO);
     
     ArrayList<Cosme_cateVO> list2 = this.cosme_cateProc.list_all(); // 카테고리 목록 가져오기
     
    for (Cosme_cateVO item : list2) {
//       System.out.println("화장품 종류 이름: " + item.getCosme_catename());
     }

   mav.addObject("list2", list2); // 모델에 카테고리 목록 추가
   mav.setViewName("/cosme/update"); 
   
   return mav;
   }

//   /**
//    * 수정 처리
//    * http://localhost:9093/cosme/update.do?cosmeno=1
//    * @return
//    */
   @RequestMapping(value="/cosme/update.do", method=RequestMethod.POST)
   public ModelAndView update_all_cosme(HttpSession session, CosmeVO cosmeVO) {

     ModelAndView mav = new ModelAndView();
     mav.setViewName("/cosme/msg");

     if (this.masterProc.isMaster(session) == true) {
       this.cosmeProc.update_all_cosme(cosmeVO);
       
       mav.addObject("cosmeno", cosmeVO.getCosmeno());
       mav.setViewName("/cosme/update");
     } else {
         mav.setViewName("/master/login_need"); // /WEB-INF/views/master/login_need.jsp
       }

       return mav;
   }
   
   /**
    * 파일 수정 처리 http://localhost:9093/cosme/update.do
    * 
    * @return
    */
   @RequestMapping(value="/cosme/update_file.do", method=RequestMethod.POST)
   public ModelAndView update(CosmeVO cosmeVO, HttpServletRequest request, HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (masterProc.isMaster(session) == true) { // 관리자로 로그인한경우
       // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
       CosmeVO cosmeVO_old = cosmeProc.cosme_read(cosmeVO.getCosmeno());
       
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = cosmeVO_old.getCosme_file_saved();  // 실제 저장된 파일명
       String thumb1 = cosmeVO_old.getCosme_file_preview();       // 실제 저장된 preview 이미지 파일명
       long size1 = 0;
          
       String upDir =  Cosme.getUploadDir(); // C:/kd/deploy/team2_v2sbm3c/cosme/storage/
       
       Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
       Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 종료
       // -------------------------------------------------------------------
       
       // ------------------------------------------------------------------------------
       // 파일 전송 코드 시작
       // ------------------------------------------------------------------------------
       String file1 = "";          // 원본 파일명 image

       // 전송 파일이 없어도 file1MF 객체가 생성됨.
       // <input type='file' class="form-control" name='file1MF' id='file1MF' 
       //           value='' placeholder="파일 선택">
       MultipartFile mf = cosmeVO.getFile1MF();
           
       file1 = mf.getOriginalFilename(); // 원본 파일명
       size1 = mf.getSize();  // 파일 크기
           
       if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
         // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
         file1saved = Upload.saveFileSpring(mf, upDir); 
         
         if (Tool.isImage(file1saved)) { // 이미지인지 검사
           // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
           thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
         }
         
       } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
         file1="";
         file1saved="";
         thumb1="";
         size1=0;
       }
           
       cosmeVO.setCosme_file(file1);   // 순수 원본 파일명
       cosmeVO.setCosme_file_saved(file1saved); // 저장된 파일명(파일명 중복 처리)
       cosmeVO.setCosme_file_preview(thumb1);      // 원본이미지 축소판
       cosmeVO.setCosme_file_size(size1);  // 파일 크기
       // -------------------------------------------------------------------
       // 파일 전송 코드 종료
       // -------------------------------------------------------------------
       

       // 다른 필요한 핸들러 메서드를 추가로 구현할 수 있습니다.

       // Call By Reference: 메모리 공유, Hashcode 전달
       int masterno = (int)session.getAttribute("masterno"); // masterno FK
       cosmeVO.setMasterno(masterno);
       int cnt = this.cosmeProc.create(cosmeVO); 

       // ------------------------------------------------------------------------------
       // PK의 return
       // ------------------------------------------------------------------------------
       if (cnt == 1) {
        // this.cosmeProc.update_cnt_add(cosmeVO.getCosmeno()); 
         mav.addObject("code", "create_success");
         mav.setViewName("/cosme/msg");
       } else {
         mav.addObject("code", "create_fail");
         mav.setViewName("/cosme/msg");
       }
       mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
       
       // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
       mav.addObject("cosmeno", cosmeVO.getCosmeno()); // redirect parameter 적용
       
       mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
       mav.setViewName("redirect:/cosme/msg.do"); 

     } else {
       mav.addObject("url", "/master/login_need"); // /WEB-INF/views/master/login_need.jsp
       mav.setViewName("redirect:/cosme/msg.do"); 
     }
     
     return mav; // forward
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
    
    // http://localhost:9093/cosme/msg.do?code=create_success
    @RequestMapping(value = "/cosme/msg.do", method = RequestMethod.GET)
    public ModelAndView msg() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/cosme/msg");
      return mav;
    }
    

}