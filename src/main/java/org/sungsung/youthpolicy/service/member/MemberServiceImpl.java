package org.sungsung.youthpolicy.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberPlusVO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.repository.MemberDAO;



@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void insert(MemberVO memberVO) {
        String encodedPwd = passwordEncoder.encode(memberVO.getPassword());
        memberVO.setPassword(encodedPwd);
        memberVO.setProvider("local");
        memberVO.setRole("admin");
        memberDAO.insert(memberVO);
    }


    @Override
    public MemberDetailDTO findMemberByLoginId(String id) {
        MemberDetailDTO member = memberDAO.selectMemberByLoginId(id).get();
        return member;
    }


    @Override
    public MemberPlusDTO checkMemberPlus(String memberLoginId) {


        return memberDAO.selectMemberPlusDTOByMemberId(memberLoginId);

    }

    @Override
    public void insertMemberPlus(MemberPlusDTO memberPlusDTO, String memberLoginId) {
        MemberPlusVO memberPlusVO = new MemberPlusVO(memberPlusDTO);
        String memberId = memberDAO.selectIdByLoginId(memberLoginId);
        memberPlusVO.setMemberId(memberId);

        memberDAO.insertMemberPlus(memberPlusVO);
    }


}

