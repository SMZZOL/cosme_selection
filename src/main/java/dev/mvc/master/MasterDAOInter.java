package dev.mvc.master;

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
  public MasterVO read(int  masterno);
  
}
