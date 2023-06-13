/**********************************/
/* Table Name: 성분 */
/**********************************/
select * from cosme;
delete from cosme;
select * from ingred;
CREATE SEQUENCE ingred_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;       
CREATE TABLE ingred(
		ingredno NUMERIC(10) NOT NULL PRIMARY KEY,
		ingredname VARCHAR(50),
		ingredeffect CHAR(300)
);

/**********************************/
/* Table Name: 화장품타입 */
/**********************************/
select * from cosmetype;
CREATE TABLE cosmetype(
		cosmetypeno NUMERIC(10) NOT NULL PRIMARY KEY,
		cosmetypename VARCHAR(20) NOT NULL
);

/**********************************/
/* Table Name: 화장품/성분 릴레이션 */
/**********************************/
CREATE TABLE cosne_ingred_relate(
		COSMENO NUMERIC(10),
		ingredno INT,
  FOREIGN KEY (ingredno) REFERENCES ingred (ingredno),
  FOREIGN KEY (COSMENO) REFERENCES COSME (COSMENO)
);

/**********************************/
/* Table Name: 화장품/타입 릴레이션 */
/**********************************/
CREATE TABLE cosme_type_relate(
		cosmetypeno INT,
		COSMENO NUMERIC(10),
  FOREIGN KEY (cosmetypeno) REFERENCES cosmetype (cosmetypeno),
  FOREIGN KEY (COSMENO) REFERENCES COSME (COSMENO)
);





