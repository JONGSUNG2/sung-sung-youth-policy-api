package org.sungsung.youthpolicy.domain.vo.member;

import lombok.Data;

@Data
public class MemberVO {
    private int id;
    private String email;
    private String name;
    private String loginId;
    private String password;
    private String provider;
    private String providerId;
    private String role;
}