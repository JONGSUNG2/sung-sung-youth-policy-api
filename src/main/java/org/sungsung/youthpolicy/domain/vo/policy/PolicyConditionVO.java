package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.Data;

@Data
public class PolicyConditionVO {
    private Long id;
    private String loginId;
    private Integer age;
    private String region;
    private String mainCategory;
    private String conditionHash;
}