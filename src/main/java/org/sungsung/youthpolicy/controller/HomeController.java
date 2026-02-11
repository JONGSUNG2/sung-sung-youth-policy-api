package org.sungsung.youthpolicy.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListDTO;
import org.sungsung.youthpolicy.service.member.MemberService;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.util.List;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final PolicyService policyService;
    private final MemberService memberService;
    @GetMapping("/")
    public String home(Model model) {
        List<PolicyListDTO> policyList = policyService.policyList();

        model.addAttribute("policyList",policyList);

        return "home";
    }
}