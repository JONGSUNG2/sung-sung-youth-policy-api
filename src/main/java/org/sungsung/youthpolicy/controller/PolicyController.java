package org.sungsung.youthpolicy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.service.policy.PolicyService;

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
}