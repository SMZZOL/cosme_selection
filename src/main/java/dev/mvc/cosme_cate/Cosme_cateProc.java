package dev.mvc.cosme_cate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


  @Component("dev.mvc.cosme_cate.Cosme_cateProc") // Controller가 사용하는 이름
  public class Cosme_cateProc implements Cosme_cateProcInter {
    @Autowired
    private Cosme_cateDAOInter cosme_cateDAO; 
    
    // 등록
    @Override
    public int create(Cosme_cateVO cosme_cateVO) {
      int cnt = this.cosme_cateDAO.create(cosme_cateVO); 
      return cnt;
    }

    // 목록
    @Override
    public ArrayList<Cosme_cateVO> list_all() {
      ArrayList<Cosme_cateVO> list = this.cosme_cateDAO.list_all();
      
      return list;
    }
  }

    
  


