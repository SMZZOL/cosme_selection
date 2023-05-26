/**********************************/
/* Table Name: 화장품 */
/**********************************/
DROP TABLE contants;

CREATE TABLE COSME(
		COSMENO                 NUMERIC(10)                     NOT NULL        PRIMARY KEY,
		BRAND                   VARCHAR(15)                     NOT NULL,
		COSMENAME               VARCHAR_IGNORECASE(50)          NOT NULL,
		COSMECATE               VARCHAR(10)                     NOT NULL,
		TYPE                    VARCHAR_IGNORECASE(20)          NOT NULL,
		AGE                     INT                             NOT NULL,
		INGERD                  VARCHAR_IGNORECASE(60)          NOT NULL,
		RDATE                   DATE                            NOT NULL
);

