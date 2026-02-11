package org.sungsung.youthpolicy.domain.dto.member;

import lombok.Data;

@Data
public class LoginDTO {
    private Long id;
    private String loginId;
    private String pwd;
}
