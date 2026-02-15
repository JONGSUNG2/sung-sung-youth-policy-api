package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;


@Data

public class PolicyCondition {

    //검색 조건
    private String policyName;
    private String mainCategory;
    private String subCategory;
    private String addressCode;
}
