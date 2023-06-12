/**********************************/
/* Table Name: 자유게시판 */
/**********************************/
DROP TABLE fboard;

CREATE TABLE fboard(
        fboardno            NUMBER(10)     NOT NULL         PRIMARY KEY,
        memberno            NUMBER(10)     NOT NULL , -- FK
        ftitle             VARCHAR(50)    NOT NULL,
        fcontent           CLOB    NOT NULL,
        rdate               DATE           NOT NULL,
        file1                VARCHAR(100)          NULL,  -- 원본 파일명 image
        file1saved            VARCHAR(100)          NULL,  -- 저장된 파일명, image
        thumb1                VARCHAR(100)          NULL,   -- preview image
        size1                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
        youtube               VARCHAR2(1000)            NULL,
        FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE fboard is '자유게시판';
COMMENT ON COLUMN fboard.noticeno is '자유게시판 번호';
COMMENT ON COLUMN fboard.memberno is '회원 번호';
COMMENT ON COLUMN fboard.ftitle is '자유게시판 제목';
COMMENT ON COLUMN fboard.fcontent is '자유게시판 내용';
COMMENT ON COLUMN fboard.rdate is '등록일';
COMMENT ON COLUMN fboard.file1 is '메인 이미지';
COMMENT ON COLUMN fboard.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN fboard.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN fboard.size1 is '메인 이미지 크기';

DROP SEQUENCE fboard_seq;

CREATE SEQUENCE fboard_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO fboard(fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1)
VALUES(fboard_seq.nextval, 1, '자유', '아무 글 작성', sysdate, 'cosme.jpg', 'cosme_1.jpg', 'cosme_t.jpg', 1000);

commit;     

-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO fboard(fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1)
VALUES(fboard_seq.nextval, 1, '자유1', '아무 글 작성', sysdate, 'cosme.jpg', 'cosme_1.jpg', 'cosme_t.jpg', 1000);
            
INSERT INTO fboard(fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1)
VALUES(fboard_seq.nextval, 1, '자유2', '사담', sysdate, 'cosme.jpg', 'cosme_1.jpg', 'cosme_t.jpg', 1000);
            
INSERT INTO fboard(fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1)
VALUES(fboard_seq.nextval, 1, '자유3', '오늘 하루', sysdate, 'cosme.jpg', 'cosme_1.jpg', 'cosme_t.jpg', 1000);

-- 유형 1 전체 목록
SELECT fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1
FROM fboard
ORDER BY fboardno ASC;
         
--userno 1번인 관리자가 등록한 레코드이며, fboardno 1번인 여행에 속한 레코드임
  FBOARDNO     USERNO FTITLE               FCONTENT                RDATE               FILE1                                         FILE1SAVED                           THUMB1               SIZE1
---------- ---------- ----------------- -------------------------------------------------- ------------------- --------------------------------------------------------------------------------------------------
         1          1 자유            아무 글 작성                  2023-05-31 11:31:58 cosme.jpg                                          cosme_1.jpg                          cosme_t.jpg         1000
         2          1 자유1             아무 글 작성              2023-05-31 11:32:50 cosme.jpg                                         cosme_1.jpg                           cosme_t.jpg             1000


--userno가 userno 테이블에 등록이 안되어 있는 번호이면 레코드 삭제 후 다시 INSERT
DELETE FROM fboard;
commit;

ROLLBACK;
-- 모든 레코드 삭제
DELETE FROM fboard;
commit;

-- 삭제
DELETE FROM fboard
WHERE fboardno = 1;
commit;

DELETE FROM fboard
WHERE fboardno=1 AND memberno = 1;

commit;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT fboardno, memberno, ftitle, fcontent, rdate, file1, file1saved, thumb1, size1
FROM fboard
WHERE fboardno = 3;

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE fboard
SET ftitle='추천!!', fcontent='직접 사용해 본 후기입니다'
WHERE fboardno = 1;

-- SUCCESS
UPDATE fboard
SET ftitle='추천!!', fcontent='직접 ''사용해 본'' 후기입니다'
WHERE fboardno = 1;

-- SUCCESS
UPDATE fboard
SET ftitle='추천', fcontent='직접 "사용해 본" 후기입니다'
WHERE fboardno = 1;

-- 삭제
DELETE FROM fboard
WHERE fboardno = 4;

commit;

-- 특정 회원에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM fboard 
WHERE userno=1;

-- 특정 회원에 속한 레코드 모두 삭제
DELETE FROM fboard
WHERE userno=1;

-- 다수의 회원에 속한 레코드 모두 삭제: IN
SELECT fboardno, memberno, ftitle
FROM fboard
WHERE userno IN(1,2,3);
  FBOARDNO     USERNO FTITLE                                            
---------- ---------- --------------------------------------------------
         1          1 추천!!                                            
         3          1 자유2                                                                                 
                                                                                                                                                                                                                    
SELECT fboardno, memberno, ftitle
FROM fboard
WHERE userno IN('1','2','3');
  FBOARDNO     USERNO FTITLE                                            
---------- ---------- --------------------------------------------------
         1          1 추천!!                                            
         3          1 자유2                                                                                         