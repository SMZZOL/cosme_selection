package dev.mvc.master;

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

}
