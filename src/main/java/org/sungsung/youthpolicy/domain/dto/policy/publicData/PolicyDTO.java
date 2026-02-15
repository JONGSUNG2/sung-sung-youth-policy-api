package org.sungsung.youthpolicy.domain.dto.policy.publicData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PolicyDTO {

    //    policy_master table :정책 기본 정보
    @JsonProperty("plcyNo") //정책 고유 ID
    private String policyId;
    @JsonProperty("plcyNm") //정책 명
    private String policyName;
    @JsonProperty("plcyKywdNm") //정책 키워드
    private String keyword;
    @JsonProperty("plcyExplnCn")    //정책 설명
    private String description;
    @JsonProperty("lclsfNm")    //정책 대분류
    private String mainCategory;
    @JsonProperty("mclsfNm")    //정책 중분류
    private String subCategory;
    @JsonProperty("zipCd")  //지역 우편번호
    private String addressCode;
    @JsonProperty("sprvsnInstCdNm") // 지역
    private String region;
    @JsonProperty("sprvsnInstPicNm")    //관리자 이름
    private String adminName;

    //    policy_support table :지원 내용
    @JsonProperty("plcySprtCn") //지원내용 요약
    private String supportSummary;
    @JsonProperty("plcyPvsnMthdCd") //지원 방식 코드
    private String supportMethodCode;
    @JsonProperty("plcyAprvSttsCd") //승인상태코드
    private String approvalStatusCode;
    @JsonProperty("sprtSclLmtYn")   //지원 규모 제한 여부
    private String supportLimitFlag;
    @JsonProperty("sprtSclCnt") //지원 인원수
    private String supportCount;
    @JsonProperty("sprtArvlSeqYn")  //선착순 여부
    private String firstComeFlag;

    //    policy_business_period table : 사업 및 지원 기간
    @JsonProperty("bizPrdSeCd") //사업 기간 유형코드
    private String businessPeriodCode ;
    @JsonProperty("aplyPrdSeCd")    //신청기간 유형코드
    private String applyPeriodCode;
    @JsonProperty("bizPrdBgngYmd")  //사업 시작일
    private String businessStartDate;
    @JsonProperty("bizPrdEndYmd")   //사업 종료일
    private String businessEndDate;
    @JsonProperty("bizPrdEtcCn")    //사업 기간 연중 모집
    private String businessPeriodType;

    //    policy_application table : 신청 정보
    @JsonProperty("plcyAplyMthdCn") //신청 방법
    private String applyMethod;
    @JsonProperty("aplyUrlAddr")    //신청 URL
    private String applyUrl;
    @JsonProperty("sbmsnDcmntCn")   //제출 서류
    private String submissionDocuments;
    @JsonProperty("etcMttrCn")  //기타 유의사항
    private String etcNotes;
    @JsonProperty("refUrlAddr1")    //참고 링크1
    private String referenceUrl1;
    @JsonProperty("refUrlAddr2")    //참고 링크2
    private String referenceUrl2;

    //    policy_eligibility table : 대상 조건
    @JsonProperty("sprtTrgtMinAge") //최소 나이
    private String minAge;
    @JsonProperty("sprtTrgtMaxAge") //최대 나이
    private String maxAge;
    @JsonProperty("sprtTrgtAgeLmtYn")   //나이 제한 여부
    private String ageLimitFlag;
    @JsonProperty("mrgSttsCd")  //혼인 상태코드
    private String marryStatusCode;
    @JsonProperty("earnCndSeCd")    //소득 조건코드
    private String incomeConditionCode;
    @JsonProperty("addAplyQlfcCndCn")   //추가 자격 요건
    private String additionalQualification;
    @JsonProperty("ptcpPrpTrgtCn")  //참여 대상 상세 설명
    private String participantTargetText;
}
