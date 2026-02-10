package org.sungsung.youthpolicy.service.policy;

import org.sungsung.youthpolicy.domain.dto.policy.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListDTO;

import java.util.List;

public interface PolicyService {
    //    DB에 공공데이터 넣기
    public void writePublicData(List<PolicyDTO> policyDTOS);
    //    정책 목록 가져오기
    public List<PolicyListDTO> policyList();
    //    정책 상세
    public PolicyDetailDTO policyDetail(String policyId);
}
