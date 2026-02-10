package org.sungsung.youthpolicy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.service.member.MemberService;

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
    public String join(@ModelAttribute MemberVO memberVO, Model model){

        memberService.insert(memberVO);
        model.addAttribute("member", memberVO);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }


}
