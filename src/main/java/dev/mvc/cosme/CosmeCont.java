package dev.mvc.cosme;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import dev.mvc.admin.AdminProcInter;
import dev.mvc.cosme_cate.*;
import dev.mvc.cosmetype.CosmetypeProcInter;
import dev.mvc.cosmetype.CosmetypeVO;
import dev.mvc.ingred.*;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;

@Controller
public class CosmeCont {
  @Autowired
  @Qualifier("dev.mvc.cosme.CosmeProc")
  private CosmeProcInter cosmeProc;
  
  @Autowired
  @Qualifier("dev.mvc.cosme_cate.Cosme_cateProc")
  private Cosme_cateProcInter cosme_cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.cosmetype.CosmetypeProc")
  private CosmetypeProcInter cosmetypeproc;
  
  @Autowired
  @Qualifier("dev.mvc.ingred.IngredProc")
  private IngredProcInter ingredproc;
  
	
@Autowired
@Qualifier("dev.mvc.review.ReviewProc")
private ReviewProcInter reviewproc;

@Autowired
@Qualifier("dev.mvc.member.MemberProc")
private MemberProcInter memberProc = null;
  
  
  
  public CosmeCont() {
    System.out.println("-> CosmeCont created.");
  }
	
//	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.GET)
//	public ModelAndView list_by_type() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/cosme/list_by_type");
//		
//		return mav;
//	}

	
//	/**
//	 * 등록 폼
//	 * http://localhost:9093/cosme/create.do
//	 * @param cosmeno
//	 * @return
//	 */
	  @RequestMapping(value="/cosme/create.do", method = RequestMethod.GET)
	  public ModelAndView create(HttpSession session) {
  	  ModelAndView mav = new ModelAndView();
     
  	  ArrayList<Cosme_cateVO> cosme_cate_list = this.cosme_cateProc.list_all(); // 카테고리 목록 가져오기
  	  ArrayList<CosmetypeVO> coseme_type_list= this.cosmetypeproc.list_all();
  	  ArrayList<IngredVO> ingred_list = this.ingredproc.ingred_list();
  	  
  	  
  	  
  	  mav.addObject("cosme_cate_list", cosme_cate_list); // 모델에 카테고리 목록 추가
  	  mav.addObject("coseme_type_list", coseme_type_list); // 모델에 화장품 타입 추가
  	  mav.addObject("ingred_list", ingred_list); // 모델에 화장품 성분 추가
      mav.setViewName("/cosme/create"); // create.jsp
      
      if (this.adminProc.isAdmin(session) == true) {
        mav.setViewName("/cosme/create");
      } else {
          mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        }
      
      return mav;
	  }
    
	  // 등록 처리
//    @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
	 @RequestMapping(value="/cosme/create.do", method=RequestMethod.POST)
    public ModelAndView create(int[] cosmetype,int[] ingred,CosmeVO cosmeVO, HttpServletRequest request, HttpSession session) {
      ModelAndView mav = new ModelAndView();
      
      if (adminProc.isAdmin(session) == true) { // 관리자로 로그인한경우
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
        int adminno = (int)session.getAttribute("adminno"); // adminno FK
        cosmeVO.setAdminno(adminno);
        int cnt = this.cosmeProc.create(cosmeVO); 
        
        
        //ingred, cosmetype relate 에 추가. 
        
        int curr_cosmeno = this.cosmeProc.last_cosmeno();
        for(int i : ingred) {
        	Cosme_IngredVO cosme_ingredvo = new Cosme_IngredVO();
        	cosme_ingredvo.setCosmeno(curr_cosmeno);
        	cosme_ingredvo.setIngredno(i);
        	this.cosmeProc.cosme_ingred_relate_insert(cosme_ingredvo);
        }
        for(int i : cosmetype) {
        	Cosme_TypeVO cosme_typevo = new Cosme_TypeVO();
        	cosme_typevo.setCosmeno(curr_cosmeno);
        	cosme_typevo.setCosmetypeno(i);
        	this.cosmeProc.cosme_type_relate_insert(cosme_typevo);
        }
//        for(int i : ingred) {
//        	Cosme_IngredVO cosme_ingredvo = null;
//        	cosme_ingredvo.setCosmeno(cosmeVO.getCosmeno());
//        	cosme_ingredvo.setIngredno(i);
//        	this.cosmeProc.cosme_ingred_relate_insert(cosme_ingredvo);
//        }
//        for(int i : cosmetype) {
//        	System.out.println(i);
//        }
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
        mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
        mav.setViewName("redirect:/cosme/msg.do"); 
      }
      
      return mav; // forward
    }
    
	 // http://localhost:9093/cosme/cosme_read.do
	 /**
	  * 조회
	  * @return
	  */
	 @RequestMapping(value="/cosme/cosme_read.do", method=RequestMethod.GET)
	 public ModelAndView comse_read(int cosmeno) {
	   ModelAndView mav = new ModelAndView();
	   
	   CosmeVO cosmeVO = this.cosmeProc.cosme_read(cosmeno);
	   
	   String brand = cosmeVO.getBrand();
	   String cosmename = cosmeVO.getCosmename();
	   String cosme_youtube = cosmeVO.getCosme_youtube();
	   
	   cosmeVO.setBrand(brand);
	   cosmeVO.setCosmename(cosmename);
	   cosmeVO.setCosme_youtube(cosme_youtube);
	   
	   long cosme_file_size = cosmeVO.getCosme_file_size();
	   cosmeVO.setCosme_file_size(cosme_file_size);
	   
	   mav.addObject("cosmeVO", cosmeVO); // request.setAttribute("cosmeVO", cosmeVO);
	   
	   return mav;
	   
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
   public ModelAndView update_cosme(int cosmeno) {
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
   public ModelAndView update_cosme(HttpSession session, CosmeVO cosmeVO) {

     ModelAndView mav = new ModelAndView();
     mav.setViewName("/cosme/msg");

     if (this.adminProc.isAdmin(session) == true) {
       int count = this.cosmeProc.update_cosme(cosmeVO);
       
       if (count == 1) {
         // request.setAttribute("code", "update_success"); // 고전적인 jsp 방법 
         mav.addObject("code", "update_success");
         mav.setViewName("/cosme/msg"); // /WEB-INF/views/cosme/msg.jsp
         
       } else {
         // request.setAttribute("code", "update_fail");
         mav.addObject("code", "update_fail");
         mav.setViewName("/cosme/msg"); // /WEB-INF/views/cosme/msg.jsp
       }

       mav.addObject("count", count);
       
     } else {
       mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
     }

       return mav;
   }
   
// /**
// * 파일 수정 폼
// * http://localhost:9093/cosme/update_file.do?cosmeno=1
// * @param cosmeno
// * @return
// */
  @RequestMapping(value="/cosme/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file_cosme(int cosmeno) {
    ModelAndView mav = new ModelAndView();
    
    CosmeVO cosmeVO = this.cosmeProc.cosme_read(cosmeno);
    mav.addObject("cosmeVO", cosmeVO);
    
    ArrayList<Cosme_cateVO> list2 = this.cosme_cateProc.list_all(); // 카테고리 목록 가져오기
    
   for (Cosme_cateVO item : list2) {
//      System.out.println("화장품 종류 이름: " + item.getCosme_catename());
    }

  mav.addObject("list2", list2); // 모델에 카테고리 목록 추가
  mav.setViewName("/cosme/update_file"); 
  
  return mav;
  }
   
   /**
    * 파일 수정 처리 http://localhost:9093/cosme/update_file.do
    * 
    * @return
    */
   @RequestMapping(value="/cosme/update_file.do", method=RequestMethod.POST)
   public ModelAndView update(CosmeVO cosmeVO, HttpServletRequest request, HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (adminProc.isAdmin(session) == true) { // 관리자로 로그인한경우
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
       int adminno = (int)session.getAttribute("adminno"); // adminno FK
       cosmeVO.setAdminno(adminno);
       int cnt = this.cosmeProc.update_file_cosme(cosmeVO); 

       // ------------------------------------------------------------------------------
       // PK의 return
       // ------------------------------------------------------------------------------
       if (cnt == 1) {
        // this.cosmeProc.update_cnt_add(cosmeVO.getCosmeno()); 
         mav.addObject("code", "update_file_success");
         mav.setViewName("/cosme/msg");
       } else {
         mav.addObject("code", "update_file_fail");
         mav.setViewName("/cosme/msg");
       }
       mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
       
       // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
       mav.addObject("cosmeno", cosmeVO.getCosmeno()); // redirect parameter 적용
       
       mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
       mav.setViewName("redirect:/cosme/msg.do"); 

     } else {
       mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
       mav.setViewName("redirect:/cosme/msg.do"); 
     }
     
     return mav; // forward
   }
   
    
    
    /**
     * 모든 카테고리의 등록된 글목록, http://localhost:9093/cosme/list_by_type.do
     * @return
     */
    // http://localhost:9093/cosme/msg.do?code=create_success
    @RequestMapping(value = "/cosme/msg.do", method = RequestMethod.GET)
    public ModelAndView msg() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/cosme/msg");
      return mav;
    }
    
    @RequestMapping(value = "/cosme/cosme_by_cate.do", method = RequestMethod.GET)
    public ModelAndView cosme_by_cate() {
    	   ModelAndView mav = new ModelAndView();
    	      ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_all();
    	      mav.addObject("cosme_list", cosme_list);
    	   
    	        mav.setViewName("/cosme/list_by_cosme_cate"); // /WEB-INF/views/cosme_cate/list_all.jsp
    	        
    	        ArrayList<Cosme_cateVO> list = this.cosme_cateProc.list_all();
    	        for(CosmeVO cosmevo: cosme_list) {
    	        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
    	        	if(reviewgrade ==null) {
    	        		reviewgrade = "0.0";
    	        	}
    	        	cosmevo.setReviewgrade(reviewgrade);
    	        }
    	        
    	        
    	        
    	        mav.addObject("list", list);   
    	        

    	   return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/cosme/cosme_by_cate.do", method = RequestMethod.POST)
    public String cosme_by_cate_sort(@RequestBody Map<String, Object> request) {
    	String cosme_cateno = (String)request.get("value");
    	System.out.println(cosme_cateno);
    	String str = "";
    	ArrayList<CosmeVO> list = this.cosmeProc.list_by_cate(cosme_cateno);
        for(CosmeVO cosmevo: list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
        }
    	for (CosmeVO cosmevo: list) {
    		
    		
    		str +="    <div class=\"product-item\" onclick=\"location.href='/cosme/read_by_cosmeno.do?cosmeno="+cosmevo.getCosmeno()+"'\">\r\n"
    				+ "      <img class=\"img-90\" src=\"/cosme/"+cosmevo.getCosme_file_saved()+"\" alt=\"상품 1 이미지\">\r\n"
    				+ "      <h3>"+cosmevo.getCosmename()+"</h3>\r\n"
    				+ "      <p>"+cosmevo.getBrand()+"</p>\r\n"
    				+"<p style=\"color:red;'\">★"+cosmevo.getReviewgrade()+"</p>"
    				+ "    </div>";
    	}
    	
    	return str;
    }
    
	// http://localhost:9093/cosme/list_by_type.do 404
	//	@PostMapping("/cosme/list_by_type.do")
    @RequestMapping(value="/cosme/list_by_ingred.do", method=RequestMethod.GET)
    public ModelAndView list_by_ingred() {
    	ModelAndView mav = new ModelAndView();
    	
    	
    	
    	ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_all();
    	mav.addObject("cosme_list", cosme_list);
        for(CosmeVO cosmevo: cosme_list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
        }
    	
    	ArrayList<IngredVO> ingred_list = this.ingredproc.ingred_list();
    	mav.addObject("ingred_list", ingred_list);
    	for (IngredVO list :ingred_list) {
    		System.out.println(list.getIngredno());
    	}
    	
    	mav.setViewName("/cosme/list_by_ingred"); // /webapp/WEB-INF/views/cosme/list_by_type.jsp
    	
    	return mav;
    }
	@ResponseBody
	@RequestMapping(value="/cosme/list_by_ingred.do" , method = RequestMethod.POST)
	public String list_by_ingred_post(@RequestBody Map<String, Object> request) {
    	String str = "";
		ArrayList<String> list= (ArrayList<String>) request.get("list");
		if(list.isEmpty()) {
			ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_all();
			for (CosmeVO cosmevo: cosme_list) {
	        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
	        	if(reviewgrade ==null) {
	        		reviewgrade = "0.0";
	        	}
	        	cosmevo.setReviewgrade(reviewgrade);
	    		str +="    <div class=\"product-item\" onclick=\"location.href='/cosme/read_by_cosmeno.do?cosmeno="+cosmevo.getCosmeno()+"'\">\r\n"
	    				+ "      <img class=\"img-90\" src=\"/cosme/"+cosmevo.getCosme_file_saved()+"\" alt=\"상품 1 이미지\">\r\n"
	    				+ "      <h3>"+cosmevo.getCosmename()+"</h3>\r\n"
	    				+ "      <p>"+cosmevo.getBrand()+"</p>\r\n"
	    	    				+"<p style=\"color:red;'\">★"+cosmevo.getReviewgrade()+"</p>"
	    				+ "    </div>";
	    	}	
			return str;
		}
		int length = list.size();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list", list);
		paramMap.put("length", length);
		
			
		ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_by_ingred(paramMap);
    	if(cosme_list.isEmpty()) {
			return "<br><h6>조건에 해당하는 상품이 없습니다<h6>";
    	}
		for (CosmeVO cosmevo: cosme_list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
    		str +="    <div class=\"product-item\" onclick=\"location.href='/cosme/read_by_cosmeno.do?cosmeno="+cosmevo.getCosmeno()+"'\">\r\n"
    				+ "      <img class=\"img-90\" src=\"/cosme/"+cosmevo.getCosme_file_saved()+"\" alt=\"상품 1 이미지\">\r\n"
    				+ "      <h3>"+cosmevo.getCosmename()+"</h3>\r\n"
    				+ "      <p>"+cosmevo.getBrand()+"</p>\r\n"
    	    				+"<p style=\"color:red;'\">★"+cosmevo.getReviewgrade()+"</p>"
    				+ "    </div>";
    	}
		
		return str;
	}
    
    @RequestMapping(value="/cosme/list_by_type.do", method=RequestMethod.GET)
    public ModelAndView list_by_type() {
    	ModelAndView mav = new ModelAndView();
    	
    	
    	
    	ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_all();
    	mav.addObject("cosme_list", cosme_list);
    	
        for(CosmeVO cosmevo: cosme_list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
        }
    	
    	ArrayList<CosmetypeVO> type_list = this.cosmetypeproc.list_all();
    	mav.addObject("type_list", type_list);
    	
    	mav.setViewName("/cosme/list_by_type"); // /webapp/WEB-INF/views/cosme/list_by_type.jsp
    	
    	return mav;
    }
    
	@ResponseBody
	@RequestMapping(value="/cosme/list_by_type.do" , method = RequestMethod.POST)
	public String listByTypePost(@RequestBody Map<String, Object> request) {
    	String str = "";
		ArrayList<String> list= (ArrayList<String>) request.get("list");
		if(list.isEmpty()) {
			ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_all();
			for (CosmeVO cosmevo: cosme_list) {
	        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
	        	if(reviewgrade ==null) {
	        		reviewgrade = "0.0";
	        	}
	        	cosmevo.setReviewgrade(reviewgrade);
	    		str +="    <div class=\"product-item\" onclick=\"location.href='/cosme/read_by_cosmeno.do?cosmeno="+cosmevo.getCosmeno()+"'\">\r\n"
	    				+ "      <img class=\"img-90\" src=\"/cosme/"+cosmevo.getCosme_file_saved()+"\" alt=\"상품 1 이미지\">\r\n"
	    				+ "      <h3>"+cosmevo.getCosmename()+"</h3>\r\n"
	    				+ "      <p>"+cosmevo.getBrand()+"</p>\r\n"
	    	    				+"<p style=\"color:red;'\">★"+cosmevo.getReviewgrade()+"</p>"
	    				+ "    </div>";
	    	}	
			

			return str;
		}
		int length = list.size();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list", list);
		paramMap.put("length", length);
		
			
		ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_by_cosmetype(paramMap);
    	if(cosme_list.isEmpty()) {
			return "<br><h6>조건에 해당하는 상품이 없습니다<h6>";
    	}
		for (CosmeVO cosmevo: cosme_list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
    		str +="    <div class=\"product-item\" onclick=\"location.href='/cosme/read_by_cosmeno.do?cosmeno="+cosmevo.getCosmeno()+"'\">\r\n"
    				+ "      <img class=\"img-90\" src=\"/cosme/"+cosmevo.getCosme_file_saved()+"\" alt=\"상품 1 이미지\">\r\n"
    				+ "      <h3>"+cosmevo.getCosmename()+"</h3>\r\n"
    				+ "      <p>"+cosmevo.getBrand()+"</p>\r\n"
    	    				+"<p style=\"color:red;'\">★"+cosmevo.getReviewgrade()+"</p>"
    				+ "    </div>";
    	}
		
		return str;
	}
    /**
     * 삭제 처리 http://localhost:9093/cosme/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/cosme/delete.do", method = RequestMethod.POST)
    public ModelAndView cosme_delete(int cosmeno) {
      ModelAndView mav = new ModelAndView();
      
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      CosmeVO cosmeVO_read = cosmeProc.cosme_read(cosmeno);
      
      String cosme_file_saved = cosmeVO_read.getCosme_file_saved();
      String cosme_file_preview = cosmeVO_read.getCosme_file_preview();
      
      String uploadDir = Cosme.getUploadDir();
      Tool.deleteFile(uploadDir, cosme_file_saved); // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, cosme_file_preview); // preview 이미지 삭제
      // -------------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------------
     
      int cnt = this.cosmeProc.cosme_delete(cosmeno); 


      mav.setViewName("redirect:/");
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      return mav;
    }
    
    @RequestMapping(value="/cosme/read_by_cosmeno.do", method=RequestMethod.GET)
    public ModelAndView read_by_cosmeno(int cosmeno) {
        ModelAndView mav = new ModelAndView();
        CosmeVO cosmevo = this.cosmeProc.cosme_read(cosmeno);
        ArrayList <CosmetypeVO> cosmetype_list = this.cosmetypeproc.cosmetype_by_cosmeno(cosmeno);
        ArrayList <IngredVO> ingredVO = this.ingredproc.ingred_by_cosmeno(cosmeno);
        ArrayList <ReviewVO> reviewlist= this.reviewproc.review_by_cosmeno(cosmeno);
        
    	for(ReviewVO Reviewvo : reviewlist) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    		LocalDate date = LocalDate.parse(Reviewvo.getReviewtime(), formatter);
    		String dateOnly = date.toString();
    		MemberVO membervo = this.memberProc.read(Reviewvo.getMemberno());
    		String name = (membervo.getMname().charAt(0))+"**";
    		Reviewvo.setReviewtime(dateOnly);
    		Reviewvo.setMname(name);
    		
    	}
        
        
        
        mav.addObject("cosmevo", cosmevo);
        mav.addObject("cosmetype_list", cosmetype_list);
        mav.addObject("ingredVO", ingredVO);
        mav.addObject("reviewlist", reviewlist);
        mav.setViewName("/cosme/cosme_read");
        return mav;
    }
    @RequestMapping(value="/cosme/list_by_commend.do", method=RequestMethod.GET)
    public ModelAndView list_by_commend(HttpSession session, int memberno) {
        ModelAndView mav = new ModelAndView();
        int cnt = this.cosmeProc.ck_memberno(memberno);
        if(cnt ==0) {
            mav.setViewName("redirect:http://localhost:8000/ais/recommend_form/?memberno="+memberno); 
            return mav;
        }
        
        ArrayList<String> list = new ArrayList<String>();
        CosmetypeVO cosmetypevo = this.cosmeProc.recommed_typeno(memberno);
        list.add(String.valueOf(cosmetypevo.getCosmetypeno()));
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list", list);
		paramMap.put("length", 1);
		
			
		ArrayList<CosmeVO> cosme_list = this.cosmeProc.list_by_cosmetype(paramMap);
		for (CosmeVO cosmevo: cosme_list) {
        	String reviewgrade = this.reviewproc.avg_grade(cosmevo.getCosmeno());
        	if(reviewgrade ==null) {
        		reviewgrade = "0.0";
        	}
        	cosmevo.setReviewgrade(reviewgrade);
        }
        mav.addObject("cosme_list", cosme_list);
        mav.addObject("cosmetypename", cosmetypevo.getCosmetypename());
        mav.setViewName("cosme/list_by_recommend");

        
        return mav;
    }
    
}