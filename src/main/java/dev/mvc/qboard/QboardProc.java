package dev.mvc.qboard;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.qboard.QboardProc")
public class QboardProc implements QboardProcInter {
  @Autowired
  private QboardDAOInter qboardDAO;
  /**
   * 등록
   */
  @Override
  public int create(QboardVO qboardVO) {
    int cnt = this.qboardDAO.create(qboardVO);
    return cnt;
  }
  /**
   * 목록
   */
  @Override
  public ArrayList<QboardVO> list_all() {
    ArrayList<QboardVO>list = this.qboardDAO.list_all();
    
    for(QboardVO qboardVO : list) {
      String qtitle = qboardVO.getQtitle();
      String qcontent = qboardVO.getQcontent();
      
      qtitle = Tool.convertChar(qtitle); //특수문자처리
      qcontent = Tool.convertChar(qcontent);
      
      qboardVO.setQtitle(qtitle);
      qboardVO.setQcontent(qcontent);
    }
    return list;
  }
  
  /**
   * 조회
   */
  @Override
  public QboardVO read(int qboardno) {
    QboardVO qboardVO = this.qboardDAO.read(qboardno);
    return qboardVO;
  }
  
  /**
   * 삭제
   */
  @Override
  public int delete(int qboardno) {
    int cnt = this.qboardDAO.delete(qboardno);
    return cnt;
  }
  /**
   * 글 수정
   */
  @Override
  public int update_text(QboardVO qboardVO) {
    int cnt = this.qboardDAO.update_text(qboardVO);
    return cnt;
  }
  
}
