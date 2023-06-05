package dev.mvc.qboard;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.qboard.QboardProc")
public class QboardProc implements QboardProcInter {
  @Autowired
  private QboardDAOInter qboardDAO;
  
  @Override
  public int create(QboardVO qboardVO) {
    int cnt = this.qboardDAO.create(qboardVO);
    return cnt;
  }
  
  @Override
  public ArrayList<QboardVO> list_all() {
    ArrayList<QboardVO>list = this.qboardDAO.list_all();
    return list;
  }

}
