CREATE TABLE CJ_USER(
	USERID INTEGER NOT NULL, 
	UNAME VARCHAR(20), 
	CONSTRAINT PK_USER PRIMARY KEY (USERID)
	);

INSERT INTO CJ_USER VALUES(1,'ling');
INSERT INTO CJ_USER VALUES(2,'pengmz');
INSERT INTO CJ_USER VALUES(3,'linsc');
INSERT INTO CJ_USER VALUES(4,'tanxq');
INSERT INTO CJ_USER VALUES(5,'linzy');
INSERT INTO CJ_USER VALUES(6,'linli');
INSERT INTO CJ_USER VALUES(7,'lindp');
INSERT INTO CJ_USER VALUES(8,'linsan');
INSERT INTO CJ_USER VALUES(9,'linsier');

DROP TABLE CJ_USER;