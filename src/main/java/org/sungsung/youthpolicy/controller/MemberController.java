package org.sungsung.youthpolicy.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;
import org.sungsung.youthpolicy.domain.dto.policy.config.MainCategory;
import org.sungsung.youthpolicy.domain.dto.policy.config.Region;
import org.sungsung.youthpolicy.domain.vo.member.MemberPlusVO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.service.member.MemberService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        log.info("join");
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO memberVO, Model model) {

        memberService.insert(memberVO);
        model.addAttribute("member", memberVO);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }


    @GetMapping("/detail")
    public String member(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/member/login";
        }
        MemberDetailDTO member = memberService.findMemberByLoginId(principal.getName());
        model.addAttribute("member", member);
        return "member/detail";
    }

    @GetMapping("/memberPlus")
    public String joinMemberPlus(Principal principal, Model model, @RequestParam("name") String name) {
        MemberPlusVO memberPlus = new MemberPlusVO();
        memberPlus.setMemberId(principal.getName());
        model.addAttribute("memberName", name);
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("memberPlus", memberPlus);
        return "/member/memberPlus";
    }

    @PostMapping("/memberPlus")
    public String joinMemberPlus(@ModelAttribute MemberPlusDTO memberPlusDTO, Model model, Principal principal) {
        memberService.insertMemberPlus(memberPlusDTO, principal.getName());
        log.info("joinMemberPlus{}", memberPlusDTO);
        return "redirect:/policy/recommend";
    }

}
