package dev.mvc.cosmetype;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.cosmetype.CosmetypeProc")
public class CosmetypeProc implements CosmetypeProcInter{
  @Autowired
  private CosmetypeDAOInter cosmetypeDAO;
  
  // 등록
  @Override
  public int create(CosmetypeVO cosmetypeVO) {
    int cnt = this.cosmetypeDAO.create(cosmetypeVO);
    return cnt;
  }

  // 목록
  @Override
  public ArrayList<CosmetypeVO> list_all() {
    ArrayList<CosmetypeVO>list = this.cosmetypeDAO.list_all();
    return list;
  }

}
