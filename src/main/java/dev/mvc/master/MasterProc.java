package dev.mvc.master;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.master.MasterProc")
public class MasterProc implements MasterProcInter{
  @Autowired
  public MasterDAOInter masterDAO;
  
  @Override
  public int login(MasterVO masterVO) {
    int cnt = this.masterDAO.login(masterVO);
    return cnt;
  }

  @Override
  public MasterVO read_by_id(String id) {
    MasterVO masterVO= this.masterDAO.read_by_id(id); 
    return masterVO;
  }
  
  @Override
  public boolean isMaster(HttpSession session) {
    boolean master_sw = false;
    
    if (session != null) {
      String master_id = (String)session.getAttribute("master_id");
      
      if (master_id != null) {
        master_sw = true; // 정상적으로 로그인 한 경우
      }
    }
    
    return master_sw;
  }

  @Override
  public MasterVO read(int masterno) {
    MasterVO masterVO = this.masterDAO.read(masterno);
    return masterVO;
  }
  

}
