package dev.mvc.fboard;

import java.util.ArrayList;

public interface FboardProcInter {
  /**
   * 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param fboardVO
   * @return
   */
	public int create(FboardVO fboardVO);
	
  /**
   *  모든 공지사항 목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
	public ArrayList<FboardVO> list_all();

}
