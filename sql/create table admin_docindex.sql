create table admin_docindex 
( token char(20), 
  doc_id number, 
  token_frequency number,
  token_offsets varchar2(512),
-- PK 는 IOT 에 필수  
  constraint pk_admin_docindex primary key(token, doc_id))
-- IOT 임을 선언  
organization index
-- INDEX ENTRY + Pyshical ROWID + 추가된 Non Key 칼럼 이 저장되는 Tablespace 지정 
tablespace users
-- 하나의 블록에서 INDEX ENTRY 와 OVERFLOW PART가 공존할수 있는 최소값
pctthreshold 20
-- INDEX ENTRY 에 저장되는 마지막 NON KEY 칼럼 
including token_frequency
-- OVERFLOW PART 가 저장되는 TABLESPACE 지정 
overflow tablespace users ; 

 

-- INDEX ENTRY 와 Overflow Part 에 대한 저장 공간 설정
alter table admin_docindex initrans 4 overflow initrans 6 ;

 

-- IOT 생성시 OVERFLOW PART 를 지정하지 않은 경우 아래처럼 추가 할 수있다.
alter table admin_docindex add overflow tablespace users2 ;

 

-- IOT 테이블 및 OVERFLOW 영역을 새로운 TABLESPACE 로 MOVE
alter table admin_docindex move tablespace users overflow tablespace users ;

 

-- LOB 이 포함된 IOT 생성 
create table admin_iot_lob
( c1 number(6) primary key,
  admin_lob CLOB )
organization index
tablespace users
lob ( admin_lob ) store as ( tablespace users ); 

 

-- LOB 이 포함된 IOT MOVE 하기
alter  table admin_iot_lob move lob ( admin_lob ) store as ( tablespace users ) ;

 

-- 2번째 Index 생성 하기
create index doc_id_index on admin_docindex(doc_id, token) tablespace users parallel 4 nologging ;
alter index doc_id_index noparallel logging



-- 출처: https://hoonsik69.tistory.com/entry/오라클-IOTIndex-Organized-Table란 [Hoony's Story]