package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;


@Data
public class PolicyListRequestDTO {


    //검색 조건
    private String policyName;
    private String mainCategory;
    private String subCategory;
    private String addressCode;

    private Integer currentPage;
    private Integer startPage;
    private Integer endPage;
    private Integer totalPage;

    private Integer startRow;  //시작 행
    private Integer pageSize;  //한 페이지에 보여줄 개수



}