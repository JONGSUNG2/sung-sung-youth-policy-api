package org.sungsung.youthpolicy.service.policy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sungsung.youthpolicy.converter.PolicyDataConverter;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyDetailDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;
import org.sungsung.youthpolicy.repository.PolicyDAO;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    private final PolicyDataConverter policyDataConverter;
    private final PolicyDAO policyDAO;
    public static Integer PAGE_SIZE=5;
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
    public List<PolicyListResponseDTO> policyList(PolicyListRequestDTO policyListRequestDTO) {

        if (policyListRequestDTO.getCurrentPage()==null){
            policyListRequestDTO.setCurrentPage(1);
        }
        Integer policyCount = policyDAO.selectPolicyCount();
        policyListRequestDTO.setTotalPage((policyCount==0)?1:(int)Math.ceil((double)policyCount/PAGE_SIZE));

        Integer pageBlockSize = 5;
        policyListRequestDTO.setStartPage(Math.max(1,policyListRequestDTO.getCurrentPage()-2));
        policyListRequestDTO.setEndPage(Math.min(policyListRequestDTO.getTotalPage(),(policyListRequestDTO.getStartPage())+pageBlockSize-1));

        policyListRequestDTO.setPageSize(PAGE_SIZE);
        policyListRequestDTO.setStartRow((policyListRequestDTO.getCurrentPage()-1)*PAGE_SIZE);
        log.info(policyListRequestDTO.toString());
        return policyDAO.selectAllPolicy(policyListRequestDTO);
    }

    @Override
    public PolicyDetailDTO policyDetail(String policyId) {
        return policyDAO.selectPolicyDetailById(policyId);
    }

}

