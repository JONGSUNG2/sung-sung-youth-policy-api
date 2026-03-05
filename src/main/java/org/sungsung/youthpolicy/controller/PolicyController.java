package org.sungsung.youthpolicy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyRecommendRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.config.MainCategory;
import org.sungsung.youthpolicy.domain.dto.policy.config.Region;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyConditionVO;
import org.sungsung.youthpolicy.service.member.MemberService;
import org.sungsung.youthpolicy.service.policy.PolicyRecommendService;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/policy/*")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {
    private final PolicyService policyService;
    private final PolicyRecommendService policyRecommendService;
    @GetMapping("/detail/{policyId}")
    public String policyDetailPage(@PathVariable("policyId")String policyId, Model model){
        model.addAttribute("policy", policyService.policyDetail(policyId));
        return "policy/policyDetail";
    }
    @GetMapping("policyList")
    public String policyListPage(Model model, PolicyListRequestDTO policyListRequestDTO){

        List<PolicyListResponseDTO> policyList = policyService.policyList(policyListRequestDTO);
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());

        model.addAttribute("policyList",policyList);
        model.addAttribute("policyListRequestDTO", policyListRequestDTO);
        return "policy/policyList";
    }
    @GetMapping("/policy/condition")
    public String policyConditionPage(Model model, PolicyConditionVO policyConditionVO){
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("policyConditionVO", policyConditionVO);
        return "policy/policyCondition";
    }
    @PostMapping("/policy/condition")
    public String policyRecommendProcess(PolicyConditionVO policyConditionVO, Principal principal){
        if (principal ==null){
            return "redirect:/member/login";
        }
        String hash = makeHash(policyConditionVO);
//      같은 조건으로 검색한 값이 있으면 추천 목록으로
        if (policyService.findRecommendPolicyByHash(hash) != null){
            return "redirect:/policy/policyRecommendList";
        }
        policyConditionVO.setLoginId(principal.getName());
        policyConditionVO.setConditionHash(hash);
        policyService.writePolicyCondition(policyConditionVO);

//       서버에 회원 조건 TBL_POLICY_CONDITION 에 저장한 후에, 로딩창으로 이동
        return "redirect:/policy/policyLoading?hash=" + hash;
    }
    @GetMapping("/policy/policyLoading")
    public String policyRecommendListPage(@RequestParam String hash, Model model) {

        // 서비스에서 AI 추천 프로세스를 한 번에 실행 (동기 or 비동기)
        policyRecommendService.processRecommendation(hash);

        // 끝나면 로딩 페이지에서 JS가 /policy/policyRecommendList 로 이동
        return "policy/policyLoading";
    }
    @GetMapping("/policy/policyRecommendList")
    public String policyRecommendListPage(Model model){
//        여기로 이동한 후에 서버에 저장된 추천 정책 목록을 볼수있다
        return "policy/policyRecommendList";
    }


//    hash 생성
    public String makeHash(PolicyConditionVO vo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(vo); // 현재 DTO 전체 직렬화
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(json.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}