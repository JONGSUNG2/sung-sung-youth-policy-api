package org.sungsung.youthpolicy.service.member;

import jakarta.servlet.http.HttpSession;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;


public interface MemberService {
    void insert(MemberVO memberVO);
    boolean loginCheck(LoginDTO loginDTO, HttpSession session);
    Optional<MemberVO> findMemberById(Long id);
}
