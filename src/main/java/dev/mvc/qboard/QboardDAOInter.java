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
   * 삭제
   * @param contentsno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int qboardno);

}
