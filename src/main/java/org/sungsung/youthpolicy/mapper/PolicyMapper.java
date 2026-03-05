package org.sungsung.youthpolicy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sungsung.youthpolicy.domain.dto.policy.*;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
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
    public List<PolicyListResponseDTO> selectPolicyList(PolicyListRequestDTO policyListRequestDTO);
//    정책 상세
    public PolicyDetailDTO selectPolicyDetailById(String policyId);
//    정책 개수
    public Integer selectPolicyCount(PolicyListRequestDTO policyListRequestDTO);

//    정책 조건 넣기
    public void insertPolicyCondition(PolicyConditionVO policyConditionVO);
//    정책 조건 중복체크 by Hash
    public PolicyConditionVO selectPolicyRecommendByHash(String hash);

//    정책 필터링 아이디 목록 가져오기
    public List<String> selectFilterPolicyIds(PolicyConditionVO policyConditionVO);
//    필터링된 정책의 아이디로 정책목록 가져오기
    public List<PolicyDTO> selectPolicyListByFilteringId(List<String> policyIds);
}
