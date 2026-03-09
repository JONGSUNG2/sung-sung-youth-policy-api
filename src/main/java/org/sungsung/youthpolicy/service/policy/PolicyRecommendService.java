package org.sungsung.youthpolicy.service.policy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyRecommendVO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyConditionVO;
import org.sungsung.youthpolicy.service.api.ai.OpenAiService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyRecommendService {

    private final PolicyService policyService;
    private final OpenAiService openAiService;

    public void processRecommendation(String hash,String memberLoginId) {

        // 1. 조건 조회
        PolicyConditionVO condition = policyService.findRecommendPolicyByHash(hash);

        // 2. 필터링된 정책 ID 리스트 조회
        List<String> policyIds = policyService.findFilteringPolicyIds(condition);

        // 3. VIEW에서 정책 상세 리스트 한번에 조회
        List<PolicyDTO> policyList = policyService.findFilteringPolicyList(policyIds);

        // 4. GPT 프롬프트 생성 및 호출
        List<PolicyRecommendVO> recommendList = openAiService.recommendPolicyByAi(policyList,memberLoginId);

        // 5. DB에 추천 결과 저장
        for (PolicyRecommendVO recommendPolicy : recommendList) {
            recommendPolicy.setLoginId(memberLoginId);
            recommendPolicy.setConditionHash(hash);
            policyService.writeRecommendPolicy(recommendPolicy);
        }
    }
}