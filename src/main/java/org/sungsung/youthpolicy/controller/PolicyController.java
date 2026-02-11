package org.sungsung.youthpolicy.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.service.member.MemberService;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.util.Optional;

@Controller
@RequestMapping("/policy/*")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

    private final PolicyService policyService;
    private final MemberService memberService;
    @GetMapping("/detail/{policyId}")
    public String policyDetailPage(@PathVariable("policyId")String policyId, Model model, HttpSession session){
        Optional<MemberVO> loginMember = memberService.findMemberById((Long)session.getAttribute("sessionId"));
        loginMember.ifPresent(member -> model.addAttribute("loginMember", member));
        model.addAttribute("policy", policyService.policyDetail(policyId));
        return "policy/policyDetail";
    }
}