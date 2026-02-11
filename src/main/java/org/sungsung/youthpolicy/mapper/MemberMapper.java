package org.sungsung.youthpolicy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    void insertMember(MemberVO memberVO);
    Optional<MemberDetailDTO> selectMemberByLoginId(String id);
}
