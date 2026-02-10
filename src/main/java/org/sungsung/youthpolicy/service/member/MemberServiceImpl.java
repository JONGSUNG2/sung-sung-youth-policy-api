package org.sungsung.youthpolicy.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.repository.MemberDAO;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void insert(MemberVO memberVO) {
        String encodedPwd = passwordEncoder.encode(memberVO.getPwd());
        memberVO.setPwd(encodedPwd);
        memberDAO.insert(memberVO);
    }

    @Override
    public boolean logincheck(LoginDTO loginDTO) {

        LoginDTO newLoginDTO = memberDAO.selectPwdByLoginId(loginDTO.getLoginId());

        if(newLoginDTO == null){
            return false;
        }

        boolean match = passwordEncoder.matches(
                loginDTO.getPwd(),
                newLoginDTO.getPwd()
        );

        return match;
    }
}
