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
   *  자유게시판에 등록된 모든 글 목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
	public ArrayList<FboardVO> list_all();
	
  /**
   *  자유게시판 조회
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
	public FboardVO read(int fboardno);
	

}
