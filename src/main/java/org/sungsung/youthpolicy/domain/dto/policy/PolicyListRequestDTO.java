package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

@Data
public class PolicyListRequestDTO {

    private Integer currentPage;
    private Integer startPage;
    private Integer endPage;
    private Integer totalPage;

    private Integer startRow;  //시작 행
    private Integer pageSize;  //한 페이지에 보여줄 개수
}
