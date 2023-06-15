package dev.mvc.master;

import java.util.ArrayList;

public interface MasterDAOInter {

  /**
   * 로그인
   * @param MasterVO
   * @return
   */
  public int login(MasterVO masterVO);
  
  /**
   * id 통한 관리자 정보
   * @param String
   * @return
   */
  public MasterVO read_by_id(String id);
  
  /**
   * masterno를 통한 관리자 정보
   * @param masterno 관리자 번호
   * @return
   */
  public MasterVO read(int masterno);
  
  /**
   *  목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<MasterVO> list();

}
