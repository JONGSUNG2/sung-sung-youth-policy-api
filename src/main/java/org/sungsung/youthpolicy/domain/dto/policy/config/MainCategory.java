package org.sungsung.youthpolicy.domain.dto.policy.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategory {

    JOB("일자리"),
    WELFARE("복지"),
    MONEY("금융"),
    CULTURE("문화"),
    HOME("주거");


    private final String name;
}
