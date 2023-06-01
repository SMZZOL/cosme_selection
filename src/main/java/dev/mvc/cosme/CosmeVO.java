package dev.mvc.cosme;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CosmeVO{
    /** 화장품 번호 */
    private int cosmeno;
    /** 브랜드 */
    private String brand = "";
    /** 화장품 이름 */
    private String cosmename = "";
    /** 등록일 */
    private int rdate;
    /** 관리자 번호 */
    private int masterno;
    /** 화장품 종류 번호 */
    private int cosme_cateno;
    /** 화장품 사진 파일 */
    private String cosme_file = "";
    /** 화장품 사진 저장 */
    private String cosme_file_saved = "";
    /** 화장품 사진 미리보기 */
    private String cosme_file_preview = "";
    /** 화장품 사진 크기 */
    private int cosme_file_size;
    /** 화장품 유튜브 영상 */
    private String cosme_youtube = "";
  

  
}
