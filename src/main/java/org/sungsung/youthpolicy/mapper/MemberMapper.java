package org.sungsung.youthpolicy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

@Mapper
public interface MemberMapper {
    void insertMember(MemberVO memberVO);
    LoginDTO selectPwdByLoginId(String loginId);
}
