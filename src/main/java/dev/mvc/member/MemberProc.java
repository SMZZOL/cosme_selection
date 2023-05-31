package dev.mvc.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  @Autowired
  private MemberDAOInter memberDAO;
  
  public MemberProc() {
    //System.out.println("-> MemeberProc created.");
  }
  
  @Override
  public int checkID(String id) {
    int cnt = this.memberDAO.checkID(id);
    return cnt;
  }
  
  @Override
  public int create(MemberVO memberVO) {
    int cnt = this.memberDAO.create(memberVO);
    return cnt;
  }
  
  @Override
  public ArrayList<MemberVO> list() {
    ArrayList<MemberVO> list = this.memberDAO.list();
    return list;
  }

}
