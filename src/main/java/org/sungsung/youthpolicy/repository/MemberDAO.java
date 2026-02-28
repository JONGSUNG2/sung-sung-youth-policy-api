package org.sungsung.youthpolicy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberPlusVO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.mapper.MemberMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void insert(MemberVO memberVO){
        memberMapper.insertMember(memberVO);
    }

    public LoginDTO selectPwdByLoginId(String loginId) {
        return memberMapper.selectPwdByLoginId(loginId);
    }
    public Optional<MemberDetailDTO> selectMemberByLoginId(String loginId){
        return memberMapper.selectMemberByLoginId(loginId);
    }

    public String selectIdByLoginId(String loginId){
        return memberMapper.selectIdByLoginId(loginId);
    }

    public MemberPlusDTO selectMemberPlusDTOByMemberId(String loginId){
        return memberMapper.selectMemberPlusDTOByMemberId(loginId);
    }

    public void insertMemberPlus(MemberPlusVO memberPlusVO){
        memberMapper.insertMemberPlus(memberPlusVO);
    }
}
