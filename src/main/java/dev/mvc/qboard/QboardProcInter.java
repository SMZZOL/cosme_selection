package dev.mvc.qboard;

import java.util.ArrayList;

public interface QboardProcInter {
  
  /**
   * 질문게시판 등록
   * @param qboardVO
   * @return
   */
  public int create(QboardVO qboardVO);
  
  /**
   * 질문게시판 전체 목록
   * @return
   */
  public ArrayList<QboardVO>list_all();

}
