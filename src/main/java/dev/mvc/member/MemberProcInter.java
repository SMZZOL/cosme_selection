package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  // 구현 클래스를 교체하기 쉬운 구조 지원

import javax.servlet.http.HttpSession;

public interface MemberProcInter {
  
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
   public int checkID(String id);
   
   /**
    * 회원 가입
    * @param memberVO
    * @return
    */
   public int create(MemberVO memberVO);
   
   /**
    * 회원 전체 목록
    * @return
    */
   public ArrayList<MemberVO> list();

}
