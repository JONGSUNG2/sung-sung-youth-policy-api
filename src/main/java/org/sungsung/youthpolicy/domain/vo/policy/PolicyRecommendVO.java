package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.Data;

@Data
public class PolicyRecommendVO {
    private Long id;
    private String loginId;
    private String policyId;
    private String policyName;
    private String region;
    private String reason;
    private String effect;
    //  회원 아이디 추가하, 추천 목록보기에서는 방금 추천 값을 가져오게하고,저장한 후에, 이전 추천 목록보기에서는 해쉬에따라 가져오게하기
    private String conditionHash;
}