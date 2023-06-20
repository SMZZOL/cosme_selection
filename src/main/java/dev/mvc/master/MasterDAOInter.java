package dev.mvc.master;

import java.util.ArrayList;
import java.util.HashMap;

public interface MasterDAOInter {

  /**
   * 로그인
   * @param MasterVO
   * @return
   */
  public int login(HashMap<String, Object> map);
  
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

  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<Object, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<Object, Object> map);
}
