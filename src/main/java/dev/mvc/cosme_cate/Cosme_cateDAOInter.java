package dev.mvc.cosme_cate;

import java.util.ArrayList;

public interface Cosme_cateDAOInter {

  /**
   * 등록
   * @param cosme_cateVO
   * @return
   */
  public int create(Cosme_cateVO cosme_cateVO); 
  

  /**
   * 전체 목록
   * @return
   */
  public ArrayList<Cosme_cateVO>list_all();
 
  /**
   *종류별 리스트
   * @return
   */
  public ArrayList<Cosme_cateVO>list_by_cate(int cosme_cateno);
  
  /**
   * 조회
   * @param cosme_cateno
   * @return
   */
  public Cosme_cateVO read(int cosme_cateno);
}
