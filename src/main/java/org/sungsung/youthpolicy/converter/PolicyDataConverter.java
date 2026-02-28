package org.sungsung.youthpolicy.converter;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;

@Data
@Service
public class PolicyDataConverter {

    public PolicyMasterVO toPolicyMaster(PolicyDTO policyDTO) {
        PolicyMasterVO vo = new PolicyMasterVO();
        vo.setPolicyId(clean(policyDTO.getPolicyId()));
        vo.setPolicyName(clean(policyDTO.getPolicyName()));
        vo.setKeyword(clean(policyDTO.getKeyword()));
        vo.setDescription(clean(policyDTO.getDescription()));
        vo.setMainCategory(clean(policyDTO.getMainCategory()));
        vo.setSubCategory(clean(policyDTO.getSubCategory()));
        vo.setAddressCode(clean(policyDTO.getAddressCode()));
        vo.setRegion(clean(policyDTO.getRegion()));
        vo.setAdminName(clean(policyDTO.getAdminName()));
        return vo;
    }

    public PolicySupportVO toPolicySupport(PolicyDTO policyDTO){
        PolicySupportVO vo = new PolicySupportVO();
        vo.setPolicyId(clean(policyDTO.getPolicyId()));
        vo.setSupportSummary(clean(policyDTO.getSupportSummary()));
        vo.setSupportMethodCode(clean(policyDTO.getSupportMethodCode()));
        vo.setApprovalStatusCode(clean(policyDTO.getApprovalStatusCode()));
        vo.setSupportLimitFlag(clean(policyDTO.getSupportLimitFlag()));
        vo.setSupportCount(Integer.parseInt(clean(policyDTO.getSupportCount())));
        vo.setFirstComeFlag(clean(policyDTO.getFirstComeFlag()));
        return vo;
    }

    public PolicyBusinessPeriodVO toPolicyBusinessPeriod(PolicyDTO policyDTO){
        PolicyBusinessPeriodVO vo = new PolicyBusinessPeriodVO();
        vo.setPolicyId(clean(policyDTO.getPolicyId()));
        vo.setBusinessPeriodCode(clean(policyDTO.getBusinessPeriodCode()));
        vo.setApplyPeriodCode(clean(policyDTO.getApplyPeriodCode()));
        vo.setBusinessStartDate(clean(policyDTO.getBusinessStartDate()));
        vo.setBusinessEndDate(clean(policyDTO.getBusinessEndDate()));
        vo.setBusinessPeriodType(clean(policyDTO.getBusinessPeriodType()));
        return vo;
    }

    public PolicyApplicationVO toPolicyApplication(PolicyDTO policyDTO){
        PolicyApplicationVO vo = new PolicyApplicationVO();
        vo.setPolicyId(clean(policyDTO.getPolicyId()));
        vo.setApplyMethod(clean(policyDTO.getApplyMethod()));
        vo.setApplyUrl(clean(policyDTO.getApplyUrl()));
        vo.setSubmissionDocuments(clean(policyDTO.getSubmissionDocuments()));
        vo.setEtcNotes(clean(policyDTO.getEtcNotes()));
        vo.setReferenceUrl1(clean(policyDTO.getReferenceUrl1()));
        vo.setReferenceUrl2(clean(policyDTO.getReferenceUrl2()));
        return vo;
    }

    public PolicyEligibilityVO toPolicyEligibility(PolicyDTO policyDTO){
        PolicyEligibilityVO vo = new PolicyEligibilityVO();
        vo.setPolicyId(clean(policyDTO.getPolicyId()));
        vo.setMinAge(parseIntSafe(policyDTO.getMinAge()));
        vo.setMaxAge(parseIntSafe(policyDTO.getMaxAge()));
        vo.setAgeLimitFlag(clean(policyDTO.getAgeLimitFlag()));
        vo.setMarryStatusCode(clean(policyDTO.getMarryStatusCode()));
        vo.setIncomeConditionCode(clean(policyDTO.getIncomeConditionCode()));
        vo.setAdditionalQualification(clean(policyDTO.getAdditionalQualification()));
        vo.setParticipantTargetText(clean(policyDTO.getParticipantTargetText()));
        return vo;
    }

//    문자열 공백 제거
    private String clean(String data) {
        return (data == null) ? null : data.trim();
    }

//    안전한 숫자 변환 (공백/NULL 처리됨)
    private int parseIntSafe(String data) {
        data = clean(data);
        if (data == null || data.isEmpty()) return 0;
        return Integer.parseInt(data);
    }
}