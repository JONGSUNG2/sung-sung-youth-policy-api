package org.sungsung.youthpolicy.domain.vo.member;

import lombok.Data;

@Data
public class MemberVO {
    private int id;
    private String name;
    private String loginId;
    private String pwd;
    private String email;
}
