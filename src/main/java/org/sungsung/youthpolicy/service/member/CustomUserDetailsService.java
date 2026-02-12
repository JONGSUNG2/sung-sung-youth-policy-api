package org.sungsung.youthpolicy.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.repository.MemberDAO;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDAO memberDAO;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        LoginDTO member = memberDAO.selectPwdByLoginId(loginId);

        if(member == null){
            throw new UsernameNotFoundException("유저 없음: " + loginId);
        }

        return User.builder()
                .username(member.getLoginId())
                .password(member.getPwd())
                .roles("USER")
                .build();
    }
}
