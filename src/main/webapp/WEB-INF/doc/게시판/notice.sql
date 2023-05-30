/**********************************/
/* Table Name: 공지사항 */
/**********************************/
DROP TABLE notice;

CREATE TABLE notice(
        noticeno            NUMBER(10)     NOT NULL         PRIMARY KEY,
        masterno            NUMBER(10)     NOT NULL , -- FK
        n_title             VARCHAR(50)    NOT NULL,
        n_content           VARCHAR(50)    NOT NULL,
        rdate               DATE           NOT NULL,
        FOREIGN KEY (masterno) REFERENCES master (masterno)
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '공지사항 번호';
COMMENT ON COLUMN notice.masterno is '관리자 번호';
COMMENT ON COLUMN notice.n_title is '공지 제목';
COMMENT ON COLUMN notice.n_content is '공지 내용';
COMMENT ON COLUMN notice.rdate is '등록일';

DROP SEQUENCE notice_seq;

CREATE SEQUENCE notice_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO notice(noticeno, masterno, n_title, n_content, rdate)
VALUES(notice_seq.nextval, 1, '필독', '규정 안내', sysdate);

commit;     

-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO notice(noticeno, masterno, n_title, n_content, rdate)
VALUES(notice_seq.nextval, 1, '공지사항', '규정 안내', sysdate);
            
INSERT INTO notice(noticeno, masterno, n_title, n_content, rdate)
VALUES(notice_seq.nextval, 1, '공지사항', '회원 등급', sysdate);
            
INSERT INTO notice(noticeno, masterno, n_title, n_content, rdate)
VALUES(notice_seq.nextval, 1, '필독', '경고 조치', sysdate);

-- 유형 1 전체 목록
SELECT noticeno, masterno, n_title, n_content, rdate
FROM notice
ORDER BY noticeno ASC;
         
--masterno 1번인 관리자가 등록한 레코드이며, noticeno 1번인 여행에 속한 레코드임
  NOTICENO   MASTERNO N_TITLE                                            N_CONTENT                                          RDATE              
---------- ---------- -------------------------------------------------- -------------------------------------------------- -------------------
         1          1 필독                                               규정 안내                                          2023-05-30 01:20:07
         2          1 공지사항                                           규정 안내                                          2023-05-30 01:21:58
         3          1 공지사항                                           회원 등급                                          2023-05-30 01:22:00
         4          1 필독                                               경고 조치                                          2023-05-30 01:22:02


--masterno가 master 테이블에 등록이 안되어 있는 번호이면 레코드 삭제 후 다시 INSERT
DELETE FROM notice;
commit;

-- 모든 레코드 삭제
DELETE FROM notice;
commit;

-- 삭제
DELETE FROM notice
WHERE noticeno = 1;
commit;

DELETE FROM n_content
WHERE noticeno=1 AND masterno = 1;

commit;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT noticeno, masterno, n_title, n_content, rdate
FROM notice
WHERE noticeno = 3;

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE notice
SET n_title='필독!!', n_content='~~'
WHERE noticeno = 1;

-- ERROR
UPDATE notice
SET n_title='필독!!', n_content="우 '리' 규칙"
WHERE noticeno = 1;
-- ERROR
UPDATE notice
SET n_title='필독!!', n_content='우 \'리\' 규칙'
WHERE noticeno = 1;

-- SUCCESS
UPDATE notice
SET n_title='필독!!', n_content='우 ''리'' 규칙'
WHERE noticeno = 1;

-- SUCCESS
UPDATE notice
SET n_title='기차를 타고', n_content='계획없이 "여행" 출발'
WHERE noticeno = 1;

-- 삭제
DELETE FROM notice
WHERE noticeno = 4;

commit;

-- 특정 관리자에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM notice 
WHERE masterno=1;

-- 특정 관리자에 속한 레코드 모두 삭제
DELETE FROM notice
WHERE masterno=1;

-- 다수의 관리자에 속한 레코드 모두 삭제: IN
SELECT noticeno, masterno, n_title
FROM notice
WHERE masterno IN(1,2,3);
  NOTICENO   MASTERNO N_TITLE                                           
---------- ---------- --------------------------------------------------
         1          1 필독!!                                            
         2          1 공지사항                                          
         3          1 공지사항                                          
         4          1 필독                                              
                                                                                                                                                                                                                    
SELECT noticeno, masterno, n_title
FROM notice
WHERE masterno IN('1','2','3');
  NOTICENO   MASTERNO N_TITLE                                           
---------- ---------- --------------------------------------------------
         1          1 필독!!                                            
         2          1 공지사항                                          
         3          1 공지사항                                          
         4          1 필독                                               