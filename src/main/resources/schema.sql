--멤버 기본정보
CREATE TABLE TBL_MEMBER(
                           ID NUMBER AUTO_INCREMENT PRIMARY KEY,
                           EMAIL VARCHAR(255) UNIQUE NOT NULL,
                           NAME VARCHAR(255) NOT NULL,
                           LOGIN_ID VARCHAR(255) UNIQUE NOT NULL,
                           PASSWORD VARCHAR(255),
                           PROVIDER VARCHAR2(25),
                           ROLE varchar2(25)

);
DROP TABLE TBL_MEMBER;
-- 정책 기본정보
CREATE TABLE TBL_POLICY_MASTER(
                                  ID NUMBER AUTO_INCREMENT CONSTRAINT PK_MASTER PRIMARY KEY ,//pk
                                  POLICY_ID VARCHAR2(30) NOT NULL UNIQUE,
                                  POLICY_NAME VARCHAR2(1000) NOT NULL ,    //정책명
                                  KEYWORD VARCHAR2(25) DEFAULT '',   //정책 키워드
                                  DESCRIPTION VARCHAR2(1000) DEFAULT '',  //정책 설명
                                  MAIN_CATEGORY VARCHAR2(25) DEFAULT '', //대분류
                                  SUB_CATEGORY VARCHAR2(25) DEFAULT '',  //중분류
                                  ADDRESS_CODE VARCHAR2(2000) DEFAULT '', //우편번호
                                  REGION VARCHAR2(25) DEFAULT '',
                                  ADMIN_NAME VARCHAR2(25) DEFAULT '',    //관리자 이름
                                  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP(0)  //생성일자

);
-- 정책 지원내용
CREATE TABLE TBL_POLICY_SUPPORT(
                                   ID NUMBER AUTO_INCREMENT CONSTRAINT PK_SUPPORT PRIMARY KEY, //pk
                                   POLICY_ID VARCHAR2(30) NOT NULL UNIQUE,
                                   SUPPORT_SUMMARY VARCHAR2(1000) DEFAULT '',  //지원 내용 요약
                                   SUPPORT_METHOD_CODE VARCHAR2(25) DEFAULT '',//지원 방식 코드
                                   APPROVAL_STATUS_CODE VARCHAR2(25) DEFAULT '',  //승인상태 코드
                                   SUPPORT_LIMIT_FLAG VARCHAR2(25) DEFAULT 'N',    //지원 규모 제한 여부
                                   SUPPORT_COUNT NUMBER DEFAULT 0,   //지원 인원수
                                   FIRST_COME_FLAG VARCHAR2(25) DEFAULT 'N'   //선착순 여부
);
-- 정책 사업 및 지원기간
CREATE TABLE TBL_POLICY_BUSINESS_PERIOD(
                                           ID NUMBER AUTO_INCREMENT CONSTRAINT PK_BUSINESS_PERIOD PRIMARY KEY, //pk
                                           POLICY_ID VARCHAR2(30) NOT NULL UNIQUE,
                                           BUSINESS_PERIOD_CODE VARCHAR2(25) DEFAULT '',  //사업 기간 유형 코드
                                           APPLY_PERIOD_CODE VARCHAR2(25) DEFAULT '', //신청 기간 유형 코드
                                           BUSINESS_START_DATE VARCHAR2(50) DEFAULT '',   //정책 시작일
                                           BUSINESS_END_DATE VARCHAR2(50) DEFAULT '' , //정책 종료일
                                           BUSINESS_PERIOD_TYPE VARCHAR2(100) DEFAULT '' //사업기간 연중모집
);
--  정책 신청정보
CREATE TABLE TBL_POLICY_APPLICATION(
                                       ID NUMBER AUTO_INCREMENT CONSTRAINT PK_POLICY_APPLICATION PRIMARY KEY, //pk
                                       POLICY_ID VARCHAR2(30) NOT NULL,
                                       APPLY_METHOD VARCHAR2(1000) DEFAULT '', //신청 방법
                                       APPLY_URL VARCHAR2(300) DEFAULT '',   //신청 url
                                       SUBMISSION_DOCUMENTS VARCHAR2(2000) DEFAULT '', //제출 서류
                                       ETC_NOTES VARCHAR2(2000) DEFAULT '',    //기타 유의사항
                                       REFERENCE_URL1 VARCHAR2(300) DEFAULT '', //참고 주소1
                                       REFERENCE_URL2 VARCHAR2(300) DEFAULT ''//참고 주소2
);

-- 정책 대상 조건
CREATE TABLE TBL_POLICY_ELIGIBILITY(
                                       ID NUMBER AUTO_INCREMENT CONSTRAINT PK_POLICY_ELIGIBILITY PRIMARY KEY,//pk
                                       POLICY_ID VARCHAR2(30) NOT NULL UNIQUE,
                                       MIN_AGE NUMBER DEFAULT 0,
                                       MAX_AGE NUMBER DEFAULT 0,
                                       AGE_LIMIT_FLAG VARCHAR2(25) DEFAULT 'N', //나이제한 여부
                                       MARRY_STATUS_CODE VARCHAR2(25) DEFAULT '', //결혼상태 코드
                                       INCOME_CONDITION_CODE VARCHAR2(100) DEFAULT '',    //소득상태코드
                                       ADDITIONAL_QUALIFICATION VARCHAR2(2000) DEFAULT '', //추가 자격요건
                                       PARTICIPANT_TARGET_TEXT VARCHAR2(2000) DEFAULT ''   //참여대상 상세설명
);
CREATE TABLE TBL_POLICY_CONDITION
(
    ID NUMBER AUTO_INCREMENT
        CONSTRAINT PK_POLICY_CONDITION PRIMARY KEY,//pk
    LOGIN_ID VARCHAR2(30) NOT NULL,
    AGE NUMBER ,
    REGION VARCHAR2(30),
    MAIN_CATEGORY VARCHAR2(30),
    CONDITION_HASH VARCHAR2(255),
    CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP(0)
);
CREATE TABLE TBL_POLICY_RECOMMEND(
                                     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     LOGIN_ID VARCHAR2(30) NOT NULL ,
                                     CONDITION_HASH VARCHAR(255) NOT NULL,
                                     POLICY_ID VARCHAR(100) NOT NULL,
                                     POLICY_NAME VARCHAR(255),
                                     REGION VARCHAR(255),
                                     REASON TEXT,
                                     EFFECT TEXT
);
-- DROP TABLE TBL_POLICY_MASTER;
-- DROP TABLE TBL_POLICY_SUPPORT;
-- DROP TABLE TBL_POLICY_APPLICATION;
-- DROP TABLE TBL_POLICY_BUSINESS_PERIOD;
-- DROP TABLE TBL_POLICY_ELIGIBILITY;
-- DROP TABLE TBL_POLICY_CONDITION;
DROP TABLE TBL_POLICY_RECOMMEND;

SELECT * FROM TBL_MEMBER;
SELECT * FROM TBL_POLICY_MASTER;
SELECT * FROM TBL_POLICY_SUPPORT;
SELECT * FROM TBL_POLICY_APPLICATION;
SELECT * FROM TBL_POLICY_BUSINESS_PERIOD;
SELECT * FROM TBL_POLICY_ELIGIBILITY;
SELECT * FROM TBL_POLICY_CONDITION;
SELECT * FROM TBL_POLICY_RECOMMEND;


-- 나이제한이 'Y' 인 정책 모두와 'N' 이면서 나이가 ??이고 min,max 사이의 정책들
-- 지역코드가 ??로 시작하는 정책들
-- 소득 조건코드가 '0043001' 소득 기준 제한 없음이면서 '??'에 맞는 정책들

SELECT TPM.POLICY_ID FROM TBL_POLICY_MASTER TPM
                              INNER JOIN TBL_POLICY_ELIGIBILITY TPE on TPM.POLICY_ID = TPE.POLICY_ID
WHERE (AGE_LIMIT_FLAG = 'Y'OR
       (AGE_LIMIT_FLAG = 'N' AND 20 BETWEEN MIN_AGE AND MAX_AGE))
  AND (TPM.ADDRESS_CODE LIKE CONCAT(51,'%'))
  AND MAIN_CATEGORY LIKE CONCAT('%', '일자리', '%');

--   AND (TPE.INCOME_CONDITION_CODE ='0043001' AND TPE.INCOME_CONDITION_CODE = '받아온 다른 소득조건코드');
SELECT TPM.POLICY_ID FROM TBL_POLICY_MASTER TPM
                              INNER JOIN  TBL_POLICY_ELIGIBILITY TPE ON TPM.POLICY_ID = TPE.POLICY_ID;



CREATE OR REPLACE VIEW POLICY_AI_VIEW AS
SELECT
    m.POLICY_ID,
    m.POLICY_NAME,
    m.KEYWORD,
    m.DESCRIPTION,
    m.MAIN_CATEGORY,
    m.SUB_CATEGORY,
    m.ADDRESS_CODE,
    m.REGION,
    m.ADMIN_NAME,

    s.SUPPORT_SUMMARY,
    s.SUPPORT_METHOD_CODE,
    s.APPROVAL_STATUS_CODE,
    s.SUPPORT_LIMIT_FLAG,
    s.SUPPORT_COUNT,
    s.FIRST_COME_FLAG,

    b.BUSINESS_PERIOD_CODE,
    b.APPLY_PERIOD_CODE,
    b.BUSINESS_START_DATE,
    b.BUSINESS_END_DATE,
    b.BUSINESS_PERIOD_TYPE,

    a.APPLY_METHOD,
    a.APPLY_URL,
    a.SUBMISSION_DOCUMENTS,
    a.ETC_NOTES,
    a.REFERENCE_URL1,
    a.REFERENCE_URL2,

    e.MIN_AGE,
    e.MAX_AGE,
    e.AGE_LIMIT_FLAG,
    e.MARRY_STATUS_CODE,
    e.INCOME_CONDITION_CODE,
    e.ADDITIONAL_QUALIFICATION,
    e.PARTICIPANT_TARGET_TEXT
FROM
    TBL_POLICY_MASTER m
        LEFT JOIN TBL_POLICY_SUPPORT s ON m.POLICY_ID = s.POLICY_ID
        LEFT JOIN TBL_POLICY_BUSINESS_PERIOD b ON m.POLICY_ID = b.POLICY_ID
        LEFT JOIN TBL_POLICY_APPLICATION a ON m.POLICY_ID = a.POLICY_ID
        LEFT JOIN TBL_POLICY_ELIGIBILITY e ON m.POLICY_ID = e.POLICY_ID;

select *
from POLICY_AI_VIEW;
