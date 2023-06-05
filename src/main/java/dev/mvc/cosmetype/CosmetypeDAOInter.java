package dev.mvc.cosmetype;

import java.util.ArrayList;;

public interface CosmetypeDAOInter {

  
  /**
   * 등록
   * @param cosmetypeVO
   * @return
   */
  public int create(CosmetypeVO cosmetypeVO); 
  

  /**
   * 전체 목록
   * @return
   */
  public ArrayList<CosmetypeVO>list_all();
}
