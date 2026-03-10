package org.sungsung.youthpolicy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyRecommendListDTO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
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
    public Integer selectPolicyCount(PolicyListRequestDTO policyListRequestDTO){
        return policyMapper.selectPolicyCount(policyListRequestDTO);
    }
    //    정책 조건 넣기
    public void insertPolicyCondition(PolicyConditionVO policyConditionVO){
        policyMapper.insertPolicyCondition(policyConditionVO);
    }
    //    정책 조건 중복체크
    public PolicyConditionVO selectPolicyRecommendByHsh(String hash){
        return policyMapper.selectPolicyConditionByHash(hash);
    }
    //    정책 필터링 목록
    public List<String> selectFilterPolicyId(PolicyConditionVO policyConditionVO){
        return policyMapper.selectFilterPolicyIds(policyConditionVO);
    }
    //    필터링된 정책의 아이디로 정책목록 가져오기
    public PolicyDTO selectPolicyListByFilteringId(String policyId){
        return policyMapper.selectPolicyListByFilteringId(policyId);
    }
    //    AI 추천 정책 삽입
    public void insertRecommendPolicy(PolicyRecommendVO policyRecommendVO){
        policyMapper.insertRecommendPolicy(policyRecommendVO);
    }
    //    AI 추천 정책 목록 조회
    public List<PolicyRecommendListDTO> selectRecommendPolicyList(String hash){
        return policyMapper.selectRecommendPolicyList(hash);
    }

}