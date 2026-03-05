package org.sungsung.youthpolicy.service.member;

import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;


public interface MemberService {
    void insert(MemberVO memberVO);
    LoginDTO login(LoginDTO loginDTO);
    MemberDetailDTO findMemberByLoginId(String id);
}
