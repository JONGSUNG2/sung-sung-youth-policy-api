package org.sungsung.youthpolicy.service.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.vo.MemberVO;
import org.sungsung.youthpolicy.repository.MemberDAO;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public boolean loginCheck(LoginDTO loginDTO,HttpSession session) {

        LoginDTO findLoginDTO = memberDAO.selectPwdByLoginId(loginDTO.getLoginId());
        if (passwordEncoder.matches(loginDTO.getPwd(), findLoginDTO.getPwd())){
            session.setAttribute("sessionId", findLoginDTO.getId());
            log.info("loginSuccess");
            return true;
        }
        log.info("loginFail");
        return false;
    }

}
