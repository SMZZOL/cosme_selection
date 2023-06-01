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
  

}
