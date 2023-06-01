DROP TABLE member;
/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		memberno    NUMERIC(10) NOT NULL PRIMARY KEY,
		id        VARCHAR(20) NOT NULL,
		passwd    VARCHAR(70) NOT NULL,
		email     VARCHAR(70) NOT NULL,
		mname     VARCHAR(30) NOT NULL,
		tel       VARCHAR(15) NOT NULL,
		zipcode   VARCHAR(10) NOT NULL,
         mdate    DATE NOT NULL,
		address1  VARCHAR(80) NOT NULL,
		address2  VARCHAR(60) NOT NULL,
		grade     NUMERIC(2) NOT NULL
);

COMMENT ON COLUMN MEMBER.memberno is '회원 번호';
COMMENT ON COLUMN MEMBER.id is '아이디';
COMMENT ON COLUMN MEMBER.passwd is '패스워드';
COMMENT ON COLUMN MEMBER.email is '이메일';
COMMENT ON COLUMN MEMBER.mname is '성명';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.ZIPCODE is '우편번호';
COMMENT ON COLUMN MEMBER.ADDRESS1 is '주소1';
COMMENT ON COLUMN MEMBER.ADDRESS2 is '주소2';
COMMENT ON COLUMN MEMBER.mdate is '가입일';
COMMENT ON COLUMN MEMBER.grade is '등급';

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq
  START WITH 1            -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999     -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                 -- 2번은 메모리에서만 계산
  NOCYCLE;                -- 다시 1부터 생성되는 것을 방지
  
1. 등록

1) id 중복 확인(null 값을 가지고 있으면 count에서 제외됨)
SELECT COUNT(id)
FROM member
WHERE id='user1';

SELECT COUNT(id) as cnt
FROM member
WHERE id='user1';

2) 등록
-- 회원 관리용 계정, Q/A 용 계정
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode,
                                 address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user1', '1234','user1@gmail.com', '회원1', '000-0000-0000', '12345',
             '서울시 종로구', '관철동', sysdate, 1);
 
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode,
                                address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user2', '69017000','user2@gmail.com', '회원2', '000-0000-0000', '12345',
             '서울시 노원구', '노원동', sysdate, 1);


-- 개인 회원 테스트 계정

INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user3', '1234', 'user3@gmail.com', '태양', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user4','1234','user4@gmail.com', '바람', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user5', '1234',  'user3@gmail.com', '바다', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);


-- 부서별(그룹별) 공유

INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'team1', '1234','team1@gmail.com', '개발팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'team2', '1234','team2@gmail.com', '웹퍼블리셔팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'team3', '1234','team3@gmail.com', '디자인팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
COMMIT;

-- 검색을 하지 않는 경우, 전체 목록 출력

SELECT memberno, id, passwd,email, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
ORDER BY grade ASC, id ASC;

3. 조회
 
1) user1@gmail.com 사원 정보 보기
SELECT memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE memberno = 1;

SELECT memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE id = 'user1@gmail.com';


SELECT memberno, id, passwd, email, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE id = 'user1@gmail.com';

4. 수정
UPDATE member 
SET id='user5', mname='아로미', tel='111-1111-1111', zipcode='00000',
    address1='경기도', address2='파주시', grade=14
WHERE memberno=1;

COMMIT;

5. 특정 회원 삭제
DELETE FROM member
WHERE memberno=3;

COMMIT;

6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=4 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE memberno=4;

COMMIT;

7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE id='user1@gmail.com' AND passwd='1234';
 cnt
 ---
   0

