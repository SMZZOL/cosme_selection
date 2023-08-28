package dev.mvc.cosme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.cosme_cate.*;
import dev.mvc.cosmetype.CosmetypeVO;
import dev.mvc.cosme.*;

public interface CosmeProcInter {
  /**
   * 화장품 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param contentsVO
   * @return
   */
  public int create(CosmeVO cosmeVO);
  
  /**
   * 화장품의 등록된 글목록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public  ArrayList<CosmeVO> cosme_all();
  
  /**
   * 조회
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param cosmeno
   * @return
   */
  public CosmeVO cosme_read(int cosmeno);
  
  /**
   * 화장품 리스트별 글목록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<CosmeVO> cosme_cate_all();
  
  
  /**
   * 전체 수정
   * @param cosmeVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update_all_cosme(CosmeVO cosmeVO);
  
  /**
   * 파일 제외 수정
   * @param cosmeVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update_cosme(CosmeVO cosmeVO);
  
  /**
   * 파일 수정
   * @param cosmeVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update_file_cosme(CosmeVO cosmeVO);
  
  /**
   * 삭제
   * @param cosmeno
   * @return 삭제된 레코드 갯수를 리턴
   */
  public int cosme_delete(int csomeno);
  
  /**
   * 글수 증가 
   * @param cosmeno
   * @return
   */
  public int update_cnt_add(int cosmeno);
  
  /**
   * 글수 감소
   * @param cosmeno
   * @return
   */
  public int update_cnt_sub(int cosmeno);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<CosmeVO>cate_all();
  
  //--김세민 화장품 작업--
  //(구)타입별 리스트
  public ArrayList<CosmeVO>list_by_type(String cosmetype);
  //종류별 리스트
  public ArrayList<CosmeVO>list_by_cate(String cosme_cateno);
  //화장품 등록시 성분 연계 등록
  public void cosme_ingred_relate_insert(Cosme_IngredVO cosme_ingredvo);
  //화장품 등록시 타입 연계 등록
  public void cosme_type_relate_insert(Cosme_TypeVO cosme_typeVO);
  //마지막 등록된 cosmeno 반환
  public int last_cosmeno();
  //타입별 ㄹ리스트
  public ArrayList<CosmeVO> list_by_cosmetype(Map<String, Object> paramMap);
  // 성분별 리스트
  public ArrayList<CosmeVO> list_by_ingred(Map<String, Object> paramMap);
  //리스트 전부
  public ArrayList<CosmeVO> list_all();
  
  public int ck_memberno (int memberno);
  
  public CosmetypeVO recommed_typeno(int memberno);
  
}
