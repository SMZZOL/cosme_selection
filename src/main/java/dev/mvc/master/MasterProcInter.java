package dev.mvc.master;

import javax.servlet.http.HttpSession;

public interface MasterProcInter {
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
   * 관리자인지 체크
   * @param session
   * @return
   */
  public boolean isMaster(HttpSession session);
  
  /**
   * masterno를 통한 관리자 정보
   * @param masterno 관리자 번호
   * @return
   */
  public MasterVO read(int  masterno);

}
