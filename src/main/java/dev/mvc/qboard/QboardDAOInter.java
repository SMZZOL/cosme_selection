package dev.mvc.qboard;

import java.util.ArrayList;

import dev.mvc.cosmetype.CosmetypeVO;

public interface QboardDAOInter {
  
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
  
  /**
   * 조회
   * @param qboardno
   * @return
   */
 public QboardVO read(int qboardno);

}
