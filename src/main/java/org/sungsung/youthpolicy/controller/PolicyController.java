package org.sungsung.youthpolicy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyRecommendListDTO;
import org.sungsung.youthpolicy.domain.dto.policy.config.MainCategory;
import org.sungsung.youthpolicy.domain.dto.policy.config.Region;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyConditionVO;
import org.sungsung.youthpolicy.service.policy.PolicyRecommendService;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/policy")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

    private final PolicyService policyService;
    private final PolicyRecommendService policyRecommendService;

    //     *  정책 상세 페이지
    @GetMapping("/detail/{policyId}")
    public String policyDetailPage(@PathVariable("policyId") String policyId, Model model) {
        model.addAttribute("policy", policyService.policyDetail(policyId));
        return "policy/policyDetail";
    }

    //     *  정책 목록 페이지
    @GetMapping("/policyList")
    public String policyListPage(Model model, PolicyListRequestDTO policyListRequestDTO) {
        List<PolicyListResponseDTO> policyList = policyService.policyList(policyListRequestDTO);

        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("policyList", policyList);
        model.addAttribute("policyListRequestDTO", policyListRequestDTO);

        return "policy/policyList";
    }

    //     *  정책 추천 조건 입력 페이지
    @GetMapping("/condition")
    public String policyConditionPage(Model model, PolicyConditionVO policyConditionVO) {
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("policyConditionVO", policyConditionVO);

        return "policy/policyCondition";
    }

    //     *  추천 조건 제출
    @PostMapping("/condition")
    public String policyRecommendProcess(PolicyConditionVO policyConditionVO, Principal principal) {
        if (principal == null) {
            return "redirect:/member/login";
        }

        String hash = makeHash(policyConditionVO);

        if (policyService.findRecommendPolicyByHash(hash) != null) {
            return "redirect:/policy/policyRecommendList?hash=" + hash;
        }

        policyConditionVO.setLoginId(principal.getName());
        policyConditionVO.setConditionHash(hash);
        policyService.writePolicyCondition(policyConditionVO);

        return "redirect:/policy/policyLoading?hash=" + hash;
    }

    //     *  AI 로딩 페이지
    @GetMapping("/policyLoading")
    public String policyLoadingPage(@RequestParam String hash, Model model) {
        model.addAttribute("hash", hash);
        return "policy/policyLoading";
    }

    //     *  AI 추천 실행 (비동기)
    @GetMapping("/recommendAi")
    @ResponseBody
    public ResponseEntity<?> policyRecommendAi(@RequestParam String hash,Principal principal) {

        if (principal == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean findPolicies = policyRecommendService.processRecommendation(hash,principal.getName());
        if (!findPolicies) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    //     *  추천 정책 목록 페이지
    @GetMapping("/policyRecommendList")
    public String policyRecommendListPage(@RequestParam String hash,Model model) {
        List<PolicyRecommendListDTO> recommendList = policyService.findRecommendPolicyList(hash);
        model.addAttribute("recommendList", recommendList);
        return "policy/policyRecommendList";
    }

    //     *  HASH 생성 메서드
    public String makeHash(PolicyConditionVO vo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(vo);
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