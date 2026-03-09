package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class PolicyDetailDTO {
    //     table master
    //    정책이름
    private String policyName;
    //    대분류
    private String mainCategory;
    //    중분류
    private String subCategory;
    //    정책 키워드
    private String keyword;
    //    정책 설명
    private String description;
    //    지역
    private String region;
    //    관리자 이름
    private String adminName;


    //    table support
    //    정책 내용 요약
    private String supportSummary;
    //    정책 지원규모 제한
    private String supportLimitFlag;
    //    정책 지원자 수
    private Integer supportCount;
    //    지원 순서 혜택 여부
    private String firstComeFlag;

    //    table application
    //    정책 지원방법 또는 기간
    private String applyMethod;
    //    정책 신청 url
    private String applyUrl;
    //    정책 제출 서류
    private String submissionDocuments;
    //    정책 기타정보
    private String etc_Notes;
    //    정책 참고 링크 1
    private String referenceUrl1;
    //    정책 참고 링크 2
    private String referenceUrl2;

    //    table business period
    //    시작 날짜
    private String businessStartDate;
    //    종료 날짜
    private String businessEndDate;
    //    신청 기간 연중 모집 여부
    private String businessPeriodType;

    //    table eligibility
    //    최소 나이
    private String minAge;
    //    최대 나이
    private String maxAge;
    //    추가 조건
    private String additionalQualification;
    //    참여 대상 상세 설명
    private String participantTargetText;

    public String formatStartDate() {
        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        if (this.businessStartDate.isEmpty()){
            this.businessStartDate = "-";
            return this.businessStartDate;
        }
        return LocalDate.parse(this.businessStartDate, input).format(output);
    }
    public String formatEndDate() {
        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        if(this.businessEndDate.isEmpty()){
            this.businessEndDate = "-";
            return this.businessEndDate;
        }
        return LocalDate.parse(this.businessEndDate, input).format(output);
    }

}