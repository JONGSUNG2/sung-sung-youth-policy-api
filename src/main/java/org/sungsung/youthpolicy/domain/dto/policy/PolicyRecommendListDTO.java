package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

@Data
public class PolicyRecommendListDTO {
    private String policyId;
    private String loginId;
    private String age;
    private String mainCategory;

    private String policyName;
    private String region;
    private String reason;
    private String effect;

}