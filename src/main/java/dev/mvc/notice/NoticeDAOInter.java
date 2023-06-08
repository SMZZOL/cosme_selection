package dev.mvc.notice;

import java.util.ArrayList;

public interface NoticeDAOInter {
	
  /**
   * 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param noticeVO
   * @return
   */
	public int create(NoticeVO noticeVO);
	
  /**
   *  모든 공지사항 목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<NoticeVO> list_all();
  
  /**
   *  공지사항 조회
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public NoticeVO read(int noticeno);

}
