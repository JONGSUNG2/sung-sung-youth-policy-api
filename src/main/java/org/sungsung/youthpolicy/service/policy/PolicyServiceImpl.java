package org.sungsung.youthpolicy.service.policy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.converter.PolicyDataConverter;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;
import org.sungsung.youthpolicy.repository.PolicyDAO;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    private final PolicyDataConverter policyDataConverter;
    private final PolicyDAO policyDAO;
    @Override
    //    DB에 공공데이터 넣기
    public void writePublicData(List<PolicyDTO> policyDTOS) {
//        컨버터 사용
        for (PolicyDTO policyDTO : policyDTOS) {
            PolicyMasterVO policyMasterVO = policyDataConverter.toPolicyMaster(policyDTO);
            PolicySupportVO policySupportVO = policyDataConverter.toPolicySupport(policyDTO);
            PolicyBusinessPeriodVO policyBusinessPeriodVO = policyDataConverter.toPolicyBusinessPeriod(policyDTO);
            PolicyApplicationVO policyApplicationVO = policyDataConverter.toPolicyApplication(policyDTO);
            PolicyEligibilityVO policyEligibilityVO = policyDataConverter.toPolicyEligibility(policyDTO);

            policyDAO.insertToMaster(policyMasterVO);
            policyDAO.insertToSupport(policySupportVO);
            policyDAO.insertToBusinessPeriod(policyBusinessPeriodVO);
            policyDAO.insertToApplication(policyApplicationVO);
            policyDAO.insertToEligibility(policyEligibilityVO);
        }
    }

    @Override
    public List<PolicyListDTO> policyList() {
        log.info(policyDAO.selectAllPolicy().toString());
        return policyDAO.selectAllPolicy();
    }

    @Override
    public PolicyDetailDTO policyDetail(String policyId) {
        return policyDAO.selectPolicyDetailById(policyId);
    }
}

