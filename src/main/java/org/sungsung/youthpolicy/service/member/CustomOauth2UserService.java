package org.sungsung.youthpolicy.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;
import org.sungsung.youthpolicy.repository.MemberDAO;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final MemberDAO memberDAO;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);


        Map<String,Object> attributes = oAuth2User.getAttributes();
        Map<String,Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String,Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String kakaoId = (Objects.requireNonNull(oAuth2User.getAttribute("id"))).toString();
        String email = (String) kakaoAccount.get("email");
        String nickname = (String) profile.get("nickname");

        if (memberDAO.selectPwdByLoginId(kakaoId) == null) {
            MemberVO member = new MemberVO();
            member.setName(nickname);
            member.setEmail(email);
            member.setLoginId(kakaoId);
            member.setPassword(null);
            member.setProvider("kakao");
            member.setRole("admin");
            memberDAO.insert(member);
        }


        return oAuth2User;
    }
}
