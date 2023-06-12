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
  
  /**
   * 조회
   * @param qboardno
   * @return
   */
 public QboardVO read(int qboardno);
 
 /**
  * 삭제
  * @param qboardno
  * @return
  */
 public int delete(int qboardno);
 
 /**
  * 글 정보 수정
  * @param qboardVO
  * @return
  */
 public int update_text(QboardVO qboardVO);


}
