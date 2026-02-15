package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

@Data
public class PolicyListResponseDTO {
    private String policyId;
    private String policyName;      // 정책명
    private String description;     // 정책 설명
}
