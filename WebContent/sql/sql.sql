--이프로젝트는 jdk 1.8사


CREATE TABLE BOARD(
SEQ NUMBER PRIMARY KEY,
NAME VARCHAR2(50) NOT NULL,
TITLE VARCHAR2(100) NOT NULL,
CONTENT CLOB NOT NULL,
PASS VARCHAR2(10) NOT NULL,
HIT NUMBER(5) NOT NULL,
REGDATE DATE NOT NULL,
orgfilename varchar2(100),
savefilename varchar2(100)
);
 
CREATE SEQUENCE BOARD_SEQ;
