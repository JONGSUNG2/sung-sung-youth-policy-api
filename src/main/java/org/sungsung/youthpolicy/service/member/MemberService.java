package org.sungsung.youthpolicy.service.member;

import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;


public interface MemberService {
    void insert(MemberVO memberVO);

    MemberDetailDTO findMemberByLoginId(String id);

}
