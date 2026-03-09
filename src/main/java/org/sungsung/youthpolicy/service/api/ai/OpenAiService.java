package org.sungsung.youthpolicy.service.api.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.sungsung.youthpolicy.domain.dto.api.ChatRequest;
import org.sungsung.youthpolicy.domain.dto.api.ChatResponse;
import org.sungsung.youthpolicy.domain.dto.api.RecommendWrapper;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyRecommendVO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenAiService {
    private final WebClient openAiWebClient;
    private final ObjectMapper objectMapper;

    public List<PolicyRecommendVO> recommendPolicyByAi(List<PolicyDTO> filteredPolicies,String memberLoginId) {

        String jsonPolicies = "";
        try {
            jsonPolicies = objectMapper.writeValueAsString(filteredPolicies);
        } catch (Exception e) {
            throw new RuntimeException("JSON 반환 오류");
        }

        String prompt = """
        아래는 이미 사용자 조건으로 필터링된 정책 리스트입니다.
        이 정책들(JSON)만 분석해서 적합한 정책을 추천하세요.

        정책 목록(JSON):
        %s
        로그인 아이디:
        %s
        요구 사항:
        1. JSON에 포함된 정책을 모두 추천하세요.
        2. 각 정책에 대해:
           - policyId
           - policyName
           - region
           - 추천 이유
           - 기대 효과
        3. 추천이유와 기대효과를 내가 입력한 조건에 맞게 설명하세요.
        4. 사용자의 로그인 아이디를 사용하여 '로그인 아이디'님 에게 추천하는 이유는 ~때문입니다. 의 형식으로 설명하시오
        반드시 아래 JSON 형식으로만 응답하세요:

        {
          "recommendList": [
            {
              "policyId": "",
              "policyName": "",
              "region": "",
              "reason": "",
              "effect": ""
            }
          ]
        }

        예외 문장, 설명, 불필요 텍스트 절대 포함하지 마세요.
        """.formatted(jsonPolicies,memberLoginId);
        ChatRequest request = new ChatRequest("gpt-5-mini", List.of(new ChatRequest.Message("user", prompt)));

        // ---------- WebClient 호출 ----------
        String aiResultJson=  openAiWebClient.post()
                .uri("/v1/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기 처리


        ChatResponse aiResponse;

        try {
            aiResponse = objectMapper.readValue(aiResultJson, ChatResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("AI JSON->DTO 변환 오류", e);
        }
//        AI응답에서 필요한 부분인 content부분만 추출
        String contentJson = aiResponse.getChoices().get(0).getMessage().getContent();

        RecommendWrapper wrapper;
        try {
//            추출한 content를  PolicyRecommendVO 의 리스트형식으로 "recommendList" 가져옴
            wrapper = objectMapper.readValue(contentJson, RecommendWrapper.class);
        } catch (Exception e) {
            throw new RuntimeException("OpenAI 응답 파싱 오류 (2차: recommendList)", e);
        }

//        받아온 리스트를 리턴
        return wrapper.getRecommendList();
    }
}
