package dev.mvc.master;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.master.MasterProc")
public class MasterProc implements MasterProcInter{
  @Autowired
  public MasterDAOInter masterDAO;
  
  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.masterDAO.login(map);
    System.out.println(cnt);
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

@Override
public ArrayList<MasterVO> list() {
	ArrayList<MasterVO> list = this.masterDAO.list();
	
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (MasterVO masterVO : list) {
      String mname = masterVO.getMname();
      int grade = masterVO.getGrade();
      
      mname = Tool.convertChar(mname);  // 특수 문자 처리
      
      masterVO.setMname(mname);
      masterVO.setGrade(grade);  

    }
	return list;
}

@Override
public int passwd_check(HashMap<Object, Object> map) {
	int cnt = this.masterDAO.passwd_check(map);
	return cnt;
}

@Override
public int passwd_update(HashMap<Object, Object> map) {
	int cnt = this.masterDAO.passwd_update(map);
	return cnt;
}
  

}
