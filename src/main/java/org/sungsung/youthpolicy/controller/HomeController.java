package org.sungsung.youthpolicy.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
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
    public String home(Model model, HttpSession session, PolicyListRequestDTO  policyListRequestDTO) {
//요청에 policyListRequest를 보내야함

        List<PolicyListResponseDTO> policyList = policyService.policyList(policyListRequestDTO);
        Optional<MemberVO> loginMember = memberService.findMemberById((Long)session.getAttribute("sessionId"));

        model.addAttribute("policyList",policyList);
        model.addAttribute("policyListRequestDTO", policyListRequestDTO);
        return "home";
    }
}