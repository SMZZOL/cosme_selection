DROP TABLE member;
/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		userno  NUMERIC(10) NOT NULL PRIMARY KEY,
		id      VARCHAR(20) NOT NULL,
		passwd  VARCHAR(70) NOT NULL,
		email   VARCHAR(70) NOT NULL,
		mname    VARCHAR(30) NOT NULL,
		mdate    DATE NOT NULL,
		grade   NUMERIC(2) NOT NULL
);

COMMENT ON COLUMN MEMBER.userno is '회원 번호';
COMMENT ON COLUMN MEMBER.id is '아이디';
COMMENT ON COLUMN MEMBER.passwd is '패스워드';
COMMENT ON COLUMN MEMBER.email is '이메일';
COMMENT ON COLUMN MEMBER.mname is '성명';
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
INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES(member_seq.nextval, 'user1', '1234','user1@gmail.com', '회원1', sysdate, 1);

INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES(member_seq.nextval, 'user2', '69017000','user2@gmail.com', '회원2', sysdate, 1);

-- 개인 회원 테스트 계정
INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'user1', '1234','user3@gmail.com', '태양', sysdate, 15);
 
INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'user2', '1234','user4@gmail.com', '바람', sysdate, 15);

INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'user3', '1234','user5@gmail.com', '바다', sysdate, 15);

-- 부서별(그룹별) 공유

INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'team1', '1234','team1@gmail.com', '개발팀', sysdate, 15);
 
INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'team2', '1234','team2@gmail.com', '디자인팀', sysdate, 15);
 
INSERT INTO member(userno, id, passwd, email, mname, mdate, grade)
VALUES (member_seq.nextval, 'team3', '1234','team3@gmail.com', '웹퍼블리셔팀', sysdate, 15);
 
COMMIT;

-- 검색을 하지 않는 경우, 전체 목록 출력
 
SELECT userno, id, passwd, email, mname, mdate, grade
FROM member
ORDER BY grade ASC, id ASC;

3. 조회
 
1) user1@gmail.com 사원 정보 보기
SELECT userno, id, passwd, mname, mdate, grade
FROM member
WHERE userno = 4;

SELECT userno, id, passwd, mname, mdate, grade
FROM member
WHERE id = 'user2@gmail.com';

4. 수정
UPDATE member 
SET id='user5', mname='아로미', grade=14
WHERE userno=3;

COMMIT;

5. 특정 회원 삭제
DELETE FROM member
WHERE userno=3;

COMMIT;

6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(userno) as cnt
FROM member
WHERE userno=4 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE userno=4;

COMMIT;

7. 로그인
SELECT COUNT(userno) as cnt
FROM member
WHERE id='user1@gmail.com' AND passwd='1234';
 cnt
 ---
   0

