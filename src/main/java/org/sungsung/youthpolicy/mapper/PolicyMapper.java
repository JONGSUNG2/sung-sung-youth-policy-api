package org.sungsung.youthpolicy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sungsung.youthpolicy.domain.dto.policy.*;
import org.sungsung.youthpolicy.domain.vo.policy.*;

import java.util.List;

@Mapper
public interface PolicyMapper {
    public void insertToMaster(PolicyMasterVO policyMasterVO);
    public void insertToSupport(PolicySupportVO policySupportVO);
    public void insertToBusinessPeriod(PolicyBusinessPeriodVO policyBusinessPeriodVO);
    public void insertToApplication(PolicyApplicationVO policyApplicationVO);
    public void insertToEligibility(PolicyEligibilityVO policyEligibilityVO);

//    정책 목록
    public List<PolicyListResponseDTO> selectPolicyList(PolicyListRequestDTO policyListRequestDTO, PolicyCondition policyCondition);
//    정책 상세
    public PolicyDetailDTO selectPolicyDetailById(String policyId);
//    정책 개수
    public Integer selectPolicyCount(PolicyCondition policyCondition);


//    정책 추천 값들 가져오기
    public List<PolicyListResponseDTO> recommendPolicyList(PolicyRecommendDTO policyRecommendDTO);
}
