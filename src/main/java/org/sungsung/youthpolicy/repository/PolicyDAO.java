package org.sungsung.youthpolicy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;
import org.sungsung.youthpolicy.mapper.PolicyMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PolicyDAO {
    private final PolicyMapper policyMapper;

    public void insertToMaster(PolicyMasterVO policyMasterVO){
        policyMapper.insertToMaster(policyMasterVO);
    }
    public void insertToSupport(PolicySupportVO policySupportVO){
        policyMapper.insertToSupport(policySupportVO);
    }
    public void insertToBusinessPeriod(PolicyBusinessPeriodVO policyBusinessPeriodVO){
        policyMapper.insertToBusinessPeriod(policyBusinessPeriodVO);
    }
    public void insertToApplication(PolicyApplicationVO policyApplicationVO){
        policyMapper.insertToApplication(policyApplicationVO);
    }
    public void insertToEligibility(PolicyEligibilityVO policyEligibilityVO){
        policyMapper.insertToEligibility(policyEligibilityVO);
    }

//    정책 목록
    public List<PolicyListResponseDTO> selectAllPolicy(PolicyListRequestDTO policyListRequestDTO){
        return policyMapper.selectPolicyList(policyListRequestDTO);
    }
//    정책 상세
    public PolicyDetailDTO selectPolicyDetailById(String policyId){
        return policyMapper.selectPolicyDetailById(policyId);
    }
//    정책 개수
    public Integer selectPolicyCount(){
        return policyMapper.selectPolicyCount();
    }
}
