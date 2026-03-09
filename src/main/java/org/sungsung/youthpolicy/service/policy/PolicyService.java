package org.sungsung.youthpolicy.service.policy;

import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyRecommendListDTO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyConditionVO;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyRecommendVO;

import java.util.List;

public interface PolicyService {
    //    DB에 공공데이터 넣기
    public void writePublicData(List<PolicyDTO> policyDTOS);
    //    정책 목록 가져오기
    public List<PolicyListResponseDTO> policyList(PolicyListRequestDTO policyListRequestDTO);
    //    정책 상세
    public PolicyDetailDTO policyDetail(String policyId);

    public void writePolicyCondition(PolicyConditionVO policyConditionVO);
    public PolicyConditionVO findRecommendPolicyByHash(String hash);

    //    정책 필터링 목록
    public List<String> findFilteringPolicyIds(PolicyConditionVO policyConditionVO);
    //    필터링된 정책의 아이디로 정책목록 가져오기
    public List<PolicyDTO> findFilteringPolicyList(List<String> policyId);
    //    AI 추천 정책 삽입
    public void writeRecommendPolicy(PolicyRecommendVO policyRecommendVO);
    //    AI 추천 정책목록 조회
    public List<PolicyRecommendListDTO> findRecommendPolicyList(String hash);
}