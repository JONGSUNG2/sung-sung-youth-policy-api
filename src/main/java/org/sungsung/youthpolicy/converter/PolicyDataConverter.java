package org.sungsung.youthpolicy.converter;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;

@Data
@Service
public class PolicyDataConverter {
    public PolicyMasterVO toPolicyMaster(PolicyDTO policyDTO) {
        PolicyMasterVO policyMasterVO = new PolicyMasterVO();
        policyMasterVO.setPolicyId(policyDTO.getPolicyId());
        policyMasterVO.setPolicyName(policyDTO.getPolicyName());
        policyMasterVO.setKeyword(policyDTO.getKeyword());
        policyMasterVO.setDescription(policyDTO.getDescription());
        policyMasterVO.setMainCategory(policyDTO.getMainCategory());
        policyMasterVO.setSubCategory(policyDTO.getSubCategory());
        policyMasterVO.setAddressCode(policyDTO.getAddressCode());
        policyMasterVO.setRegion(policyDTO.getRegion());
        policyMasterVO.setAdminName(policyDTO.getAdminName());
        return policyMasterVO;
    }
    public PolicySupportVO toPolicySupport(PolicyDTO policyDTO){
        PolicySupportVO policySupportVO = new PolicySupportVO();
        policySupportVO.setPolicyId(policyDTO.getPolicyId());
        policySupportVO.setSupportSummary(policyDTO.getSupportSummary());
        policySupportVO.setSupportMethodCode(policyDTO.getSupportMethodCode());
        policySupportVO.setApprovalStatusCode(policyDTO.getApprovalStatusCode());
        policySupportVO.setSupportLimitFlag(policyDTO.getSupportLimitFlag());
        policySupportVO.setSupportCount(Integer.parseInt(policyDTO.getSupportCount()));
        policySupportVO.setFirstComeFlag(policyDTO.getFirstComeFlag());
        return policySupportVO;

    }
    public PolicyBusinessPeriodVO toPolicyBusinessPeriod(PolicyDTO policyDTO){
        PolicyBusinessPeriodVO policyBusinessPeriodVO = new PolicyBusinessPeriodVO();
        policyBusinessPeriodVO.setPolicyId(policyDTO.getPolicyId());
        policyBusinessPeriodVO.setBusinessPeriodCode(policyDTO.getBusinessPeriodCode());
        policyBusinessPeriodVO.setApplyPeriodCode(policyDTO.getApplyPeriodCode());
        policyBusinessPeriodVO.setBusinessStartDate(policyDTO.getBusinessStartDate());
        policyBusinessPeriodVO.setBusinessEndDate(policyDTO.getBusinessEndDate());
        policyBusinessPeriodVO.setBusinessPeriodType(policyDTO.getBusinessPeriodType());
        return policyBusinessPeriodVO;
    }
    public PolicyApplicationVO toPolicyApplication(PolicyDTO policyDTO){
        PolicyApplicationVO policyApplicationVO = new PolicyApplicationVO();
        policyApplicationVO.setPolicyId(policyDTO.getPolicyId());
        policyApplicationVO.setApplyMethod(policyDTO.getApplyMethod());
        policyApplicationVO.setApplyUrl(policyDTO.getApplyUrl());
        policyApplicationVO.setSubmissionDocuments(policyDTO.getSubmissionDocuments());
        policyApplicationVO.setEtcNotes(policyDTO.getEtcNotes());
        policyApplicationVO.setReferenceUrl1(policyDTO.getReferenceUrl1());
        policyApplicationVO.setReferenceUrl2(policyDTO.getReferenceUrl2());
        return policyApplicationVO;
    }
    public PolicyEligibilityVO toPolicyEligibility(PolicyDTO policyDTO){
        PolicyEligibilityVO policyEligibilityVO = new PolicyEligibilityVO();
        policyEligibilityVO.setPolicyId(policyDTO.getPolicyId());
        policyEligibilityVO.setMinAge(Integer.parseInt(policyDTO.getMinAge()));
        policyEligibilityVO.setMaxAge(Integer.parseInt(policyDTO.getMaxAge()));
        policyEligibilityVO.setAgeLimitFlag(policyDTO.getAgeLimitFlag());
        policyEligibilityVO.setMarryStatusCode(policyDTO.getMarryStatusCode());
        policyEligibilityVO.setIncomeConditionCode(policyDTO.getIncomeConditionCode());
        policyEligibilityVO.setAdditionalQualification(policyDTO.getAdditionalQualification());
        policyEligibilityVO.setParticipantTargetText(policyDTO.getParticipantTargetText());
        return policyEligibilityVO;

    }
}
