package org.sungsung.youthpolicy.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.service.member.MemberService;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class HomeController {
    private final PolicyService policyService;
    private final MemberService memberService;
    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        List<PolicyListDTO> policyList = policyService.policyList();
        Optional<MemberVO> loginMember = memberService.findMemberById((Long)session.getAttribute("sessionId"));

        loginMember.ifPresent(member -> model.addAttribute("loginMember", member));
        model.addAttribute("policyList",policyList);

        return "home";
    }
}