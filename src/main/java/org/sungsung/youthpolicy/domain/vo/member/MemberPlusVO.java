package org.sungsung.youthpolicy.domain.vo.member;

import lombok.Data;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;

@Data
public class MemberPlusVO {

    public MemberPlusVO() {}

    public MemberPlusVO(MemberPlusDTO dto) {
        this.age = dto.getAge();
        this.region = dto.getRegion();
        this.marriage = dto.getMarriage();
        this.mainCategory = dto.getMainCategory();
    }

    private int id;
    private String memberLoginId;
    private String age;
    private String region;
    private String mainCategory;
    private String marriage;



}
