package org.sungsung.youthpolicy.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyCondition;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.dto.policy.config.MainCategory;
import org.sungsung.youthpolicy.domain.dto.policy.config.Region;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.util.List;


@Controller
@RequestMapping("/policy/*")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

    private final PolicyService policyService;
    @GetMapping("/detail/{policyId}")
    public String policyDetailPage(@PathVariable("policyId")String policyId, Model model){
        model.addAttribute("policy", policyService.policyDetail(policyId));
        return "policy/policyDetail";
    }
    @GetMapping("policyList")
    public String policyListPage(Model model, PolicyListRequestDTO policyListRequestDTO, PolicyCondition policyCondition){

        List<PolicyListResponseDTO> policyList = policyService.policyList(policyListRequestDTO, policyCondition);
        model.addAttribute("policyCondition", policyCondition);
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());

        model.addAttribute("policyList",policyList);
        model.addAttribute("policyListRequestDTO", policyListRequestDTO);
        model.addAttribute("policyCondition", policyCondition);
        return "policy/policyList";
    }
}