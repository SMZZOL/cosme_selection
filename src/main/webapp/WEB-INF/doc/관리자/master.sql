/**********************************/
/* Table Name: 관리자 */
/**********************************/
DROP TABLE master;

--자식 테이블이 있어도 무시하고 테이블 삭제
DROP TABLE cosme CASCADE CONSTRAINTS;

CREATE TABLE master(
		masterno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)     NOT NULL,
		passwd                        		VARCHAR2(15)	 NOT NULL,
		mname                         		VARCHAR2(20)	 NOT NULL,
		mdate                         		DATE		     NOT NULL,
		grade                        		NUMBER(2)		 NOT NULL
);

COMMENT ON TABLE master is '관리자';
COMMENT ON COLUMN master.managerno is '관리자 번호';
COMMENT ON COLUMN master.id is '아이디';
COMMENT ON COLUMN master.passwd is '패스워드';
COMMENT ON COLUMN master.mname is '성명';
COMMENT ON COLUMN master.mdate is '가입일';
COMMENT ON COLUMN master.grade is '등급';

DROP SEQUENCE master_seq;

CREATE SEQUENCE master_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO master(masterno, id, passwd, mname, mdate, grade)
VALUES(master_seq.nextval, 'master1', '69017000', '관리자1', sysdate, 1);

INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(master_seq.nextval, 'master2', '1234', '관리자2', sysdate, 1);

INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(master_seq.nextval, 'master3', '1234', '관리자3', sysdate, 1);

commit;

SELECT masterno, id, passwd, mname, mdate, grade FROM manager ORDER BY masterno ASC;
   MANAGERNO ID                   PASSWD          MNAME                MDATE                    GRADE
---------- -------------------- --------------- -------------------- ------------------- ----------
         1 manager1             69017000        관리자1              2023-04-27 10:42:43          1
         2 manager2             1234            관리자2              2023-04-27 10:43:17          1
         3 manager3             1234            관리자3              2023-04-27 10:43:17          1


SELECT managerno, id, passwd, mname, mdate, grade FROM manager WHERE managerno=1;
  MANAGERNO ID                   PASSWD          MNAME                MDATE                    GRADE
---------- -------------------- --------------- -------------------- ------------------- ----------
         1 manager1             69017000        관리자1              2023-04-27 10:42:43          1

UPDATE manager SET passwd='1234', mname='관리자1', mdate=sysdate, grade=1 WHERE MANAGERNO=1;

COMMIT;
         
-- 로그인, 1: 로그인 성공, 0: 로그인 실패
SELECT COUNT(*) as cnt FROM manager WHERE id='manager1' AND passwd='1234'; 

---------------------------------------------------------------
--AWS 관리자 설정
---------------------------------------------------------------
DELETE FROM manager;

INSERT INTO manager(managerno, id, passwd, mname, mdate, grade)
VALUES(manager_seq.nextval, 'manager', '0801029', '관리자1', sysdate, 1);

commit;

SELECT managerno, id, passwd, mname, mdate, grade FROM manager ORDER BY managerno ASC;

  MANAGERNO ID                   PASSWD          MNAME                MDATE                    GRADE
---------- -------------------- --------------- -------------------- ------------------- ----------
         1 manager              0801029         관리자1              2023-04-27 10:48:35          1