insert into review(reviewno,reviewcontent,cosmeno,memberno)
values (review_seq.nextval,'시발라마',);
select * from review;

select * from recommend;
delete from recommend;
select * from cosme;
delete from review;
commit;
ALTER TABLE review ADD COLUMN reviewgrade NUMBER(10);
select count(*) as cnt from review where memberno =3 and cosmeno = 38;

select avg(reviewgrade) as avg from review where cosmeno = 40;


ALTER TABLE review
ADD CONSTRAINT reviewfk
FOREIGN KEY (cosmeno) REFERENCES cosme(cosmeno)
ON DELETE CASCADE;

SELECT constraint_name
FROM user_constraints
WHERE table_name = 'REVIEW' AND constraint_type = 'R';

SYS_C007240
SYS_C007241

ALTER TABLE review
DROP CONSTRAINT SYS_C007240;

ALTER TABLE review
ADD CONSTRAINT SYS_C007240 FOREIGN KEY (cosmeno)
REFERENCES cosme(cosmeno) ON DELETE CASCADE;

ALTER TABLE review
DROP CONSTRAINT SYS_C007241;

ALTER TABLE review
ADD CONSTRAINT SYS_C007241 FOREIGN KEY (memberno)
REFERENCES member(memberno) ON DELETE CASCADE;
commit;

select * from review;





CREATE SEQUENCE review_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;   