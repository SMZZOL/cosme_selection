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
/* Table Name: 화장품 */
/**********************************/
CREATE TABLE COSME(
		COSMENO NUMERIC(10) NOT NULL PRIMARY KEY,
		BRAND VARCHAR(15) NOT NULL,
		COSMENAME VARCHAR_IGNORECASE(50) NOT NULL,
		COSMECATE VARCHAR(10) NOT NULL,
		AGE INT NOT NULL,
		RDATE DATE NOT NULL
);

/**********************************/
/* Table Name: 유저 */
/**********************************/
CREATE TABLE user(
		userno INT NOT NULL PRIMARY KEY
);

/**********************************/
/* Table Name: 사용후기 */
/**********************************/
CREATE TABLE rating(
		ratingno INT NOT NULL PRIMARY KEY,
		rainggrade INT NOT NULL,
		ratingcontent VARCHAR(200),
		userno INT,
		COSMENO NUMERIC(10),
  FOREIGN KEY (userno) REFERENCES user (userno),
  FOREIGN KEY (COSMENO) REFERENCES COSME (COSMENO)
);

/**********************************/
/* Table Name: 화장품/성분 릴레이션 */
/**********************************/
CREATE TABLE cosne_ingred_relate(
		COSMENO NUMERIC(10),
		ingredno INT,
  FOREIGN KEY (COSMENO) REFERENCES COSME (COSMENO),
  FOREIGN KEY (ingredno) REFERENCES ingred (ingredno)
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

