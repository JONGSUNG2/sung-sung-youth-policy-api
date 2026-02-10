package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

@Data
public class PolicyDetailDTO {

    private String policyName;
    private String mainCategory;
    private String subCategory;
    private String region;

    private String description;
    private String keyword;
    private String adminName;
    private String applyMethod;
}
