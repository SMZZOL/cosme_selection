package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.cosme_cate.*;
import dev.mvc.cosme.*;

public interface CosmeProcInter {
  /**
   * 화장품 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param contentsVO
   * @return
   */
  public int create(CosmeVO cosmeVO);
  
  /**
   * 화장품의 등록된 글목록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public  ArrayList<CosmeVO> cosme_all();
  
  /**
   * 화장품 리스트별 글목록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<CosmeVO> cosme_cate_all();
  
  /**
   * 전체 수정
   * @param cosmeVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update_all_cosme(CosmeVO cosmeVO);
  
  /**
   * 삭제
   * @param cosmeno
   * @return 삭제된 레코드 갯수를 리턴
   */
  public int delete(int csomeno);
  
  
}