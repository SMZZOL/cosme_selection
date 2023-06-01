/**********************************/
/* Table Name: 성분 */
/**********************************/
CREATE TABLE ingred(
		ingredno INT NOT NULL PRIMARY KEY,
		ingredname VARCHAR(50),
		ingredeffect CHAR(300)
);

/**********************************/
/* Table Name: 화장품타입 */
/**********************************/
CREATE TABLE cosmetype(
		cosmetypeno INT NOT NULL PRIMARY KEY,
		cosmetypename VARCHAR(20) NOT NULL
);

/**********************************/
/* Table Name: 화장품 종류 */
/**********************************/
CREATE TABLE cosme_cate(
		cosme_cateno INT NOT NULL PRIMARY KEY,
		cosme_catename VARCHAR(20) NOT NULL
);

/**********************************/
/* Table Name: 화장품 */
/**********************************/
CREATE TABLE COSME(
		COSMENO INTEGER(10) NOT NULL PRIMARY KEY,
		BRAND VARCHAR2(15) NOT NULL,
		COSMENAME VARCHAR2(50) NOT NULL,
		COSMECATE VARCHAR2(10) NOT NULL,
		TYPE VARCHAR2(20) NOT NULL,
		AGE INTEGER(10) NOT NULL,
		INGERD VARCHAR2(60) NOT NULL,
		RDATE DATE NOT NULL,
		masterno INTEGER(10),
		cosme_cateno INT,
  FOREIGN KEY (cosme_cateno) REFERENCES cosme_cate (cosme_cateno)
);

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		userno NUMERIC(10) NOT NULL PRIMARY KEY,
		id VARCHAR(20) NOT NULL,
		passwd VARCHAR(70) NOT NULL,
		email VARCHAR(70) NOT NULL,
		mname VARCHAR(30) NOT NULL,
		mdate DATE NOT NULL,
		grade NUMERIC(2) NOT NULL
);

/**********************************/
/* Table Name: 사용후기 */
/**********************************/
CREATE TABLE rating(
		ratingno INT NOT NULL PRIMARY KEY,
		COSMENO INTEGER(10),
		userno INT,
		rainggrade INT NOT NULL,
		ratingcontent VARCHAR(200),
  FOREIGN KEY (COSMENO) REFERENCES COSME (COSMENO),
  FOREIGN KEY (userno) REFERENCES member (userno)
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

/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE master(
		masterno NUMBER(10) NOT NULL PRIMARY KEY,
		id VARCHAR2(20) NOT NULL,
		passwd VARCHAR2(15) NOT NULL,
		mname VARCHAR2(20) NOT NULL,
		rdate DATE NOT NULL,
		authority NUMBER(5) NOT NULL
);

/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE notice(
		noticeno NUMBER(10) NOT NULL PRIMARY KEY,
		masterno NUMBER(10),
		n_title VARCHAR(50) NOT NULL,
		n_content VARCHAR(50) NOT NULL,
		rdate DATE NOT NULL,
  FOREIGN KEY (masterno) REFERENCES master (masterno)
);

/**********************************/
/* Table Name: 질문게시판 */
/**********************************/
CREATE TABLE q_board(
		q_boardno INT NOT NULL PRIMARY KEY,
		q_boardtitle VARCHAR(50) NOT NULL,
		q_boardcontent VARCHAR(500) NOT NULL,
		userno NUMERIC(10),
  FOREIGN KEY (userno) REFERENCES member (userno)
);

/**********************************/
/* Table Name: 답변 */
/**********************************/
CREATE TABLE answer(
		answerno INT NOT NULL PRIMARY KEY,
		answercontent INT,
		masterno NUMBER(10),
		q_boardno INT,
  FOREIGN KEY (masterno) REFERENCES master (masterno),
  FOREIGN KEY (q_boardno) REFERENCES q_board (q_boardno)
);

/**********************************/
/* Table Name: 자유게시판 */
/**********************************/
CREATE TABLE f_board(
		title NUMBER(10) NOT NULL PRIMARY KEY,
		userno NUMERIC(10),
		f_content VARCHAR(500) NOT NULL,
		f_title VARCHAR(50),
  FOREIGN KEY (userno) REFERENCES member (userno)
);

/**********************************/
/* Table Name: 첨부 파일 */
/**********************************/
CREATE TABLE file(
		fileno NUMBER(10) NOT NULL PRIMARY KEY,
		title NUMBER(10),
		file INT,
		file_saved INT,
		file_preview INT,
		file_size INT,
  FOREIGN KEY (title) REFERENCES f_board (title)
);

