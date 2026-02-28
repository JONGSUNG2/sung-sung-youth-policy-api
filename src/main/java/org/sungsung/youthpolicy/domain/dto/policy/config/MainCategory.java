package org.sungsung.youthpolicy.domain.dto.policy.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategory {

    JOB("일자리"),
    WELFARE("금융.복지.문화"),
    HOME("주거"),
    STUDY("교육.직업훈련"),
    PARTICIPATE("참여.기반");






    private final String name;
}
