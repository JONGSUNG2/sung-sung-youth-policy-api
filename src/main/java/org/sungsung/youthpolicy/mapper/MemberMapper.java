package org.sungsung.youthpolicy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberPlusVO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    void insertMember(MemberVO memberVO);
    LoginDTO selectPwdByLoginId(String loginId);
    Optional<MemberDetailDTO> selectMemberByLoginId(String loginId);
    String selectIdByLoginId(String loginId);

    //TBL_MEMBER_PLUS
    MemberPlusDTO selectMemberPlusDTOByMemberId(String loginId);
    void insertMemberPlus(MemberPlusVO memberPlusVO);

}
