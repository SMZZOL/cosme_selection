package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.cosme_cate.Cosme_cateVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.cosme.CosmeProc")
public class CosmeProc implements CosmeProcInter {
  @Autowired
  private CosmeDAOInter cosmeDAO;
  
  @Override
  public int create(CosmeVO cosmeVO) {
    int cnt = this.cosmeDAO.create(cosmeVO);
    
    return cnt;      
    }
  
  @Override
  public ArrayList<CosmeVO> cosme_all() {
    ArrayList<CosmeVO> list = this.cosmeDAO.cosme_all();
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (CosmeVO cosmeVO : list) {
      String cosmename = cosmeVO.getCosmename();
      
      cosmename = Tool.convertChar(cosmename);
      
      cosmeVO.setCosmename(cosmename);
    }
    
    return list;
  }
  
  @Override
  public ArrayList<CosmeVO> cosme_cate_all() {
    ArrayList<CosmeVO> list = this.cosmeDAO.cosme_cate_all();
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (CosmeVO cosmeVO : list) {
      String cosmename = cosmeVO.getCosmename();
      
      cosmename = Tool.convertChar(cosmename);
      
      cosmeVO.setCosmename(cosmename);
    }
    
    return list;
  }
  
  @Override
  public int update_all_cosme(CosmeVO cosmeVO) {
    int cnt = this.cosmeDAO.update_all_cosme(cosmeVO);
    
    return cnt;
  }
  
  @Override
  public int delete(int cosmeno) {
    int cnt = this.cosmeDAO.delete(cosmeno);
    
    return cnt;
  }
  
  @Override
  public int update_cnt_add(int cosmeno) {
    int cnt = this.cosmeDAO.update_cnt_add(cosmeno);
    return cnt;
  }
  
  @Override
  public int update_cnt_sub(int cosmeno) {
    int cnt = this.cosmeDAO.update_cnt_sub(cosmeno);
    return cnt;
  }
  
  // 목록
  @Override
  public ArrayList<CosmeVO> cate_all() {
    ArrayList<CosmeVO> list = this.cosmeDAO.cate_all();
    
    return list;
  }
  

}
