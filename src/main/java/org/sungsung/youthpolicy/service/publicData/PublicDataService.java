package org.sungsung.youthpolicy.service.publicData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.sungsung.youthpolicy.converter.PolicyDataConverter;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.RootDTO;
import org.sungsung.youthpolicy.domain.vo.policy.*;
import org.sungsung.youthpolicy.repository.PolicyDAO;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicDataService {

    private final PolicyDAO policyDAO;
    private final PolicyDataConverter policyDataConverter;
    public RootDTO getPublicData() throws JsonProcessingException {
//        한국 장학재단 공공데이터
//        JSON 행정안전부_대한민국 공공서비스(혜택) 정보
        String url = "https://api.odcloud.kr/api/gov24/v3/serviceList"
                +"?page=1"
                +"&perPage=10"
                +"&returnType=json"
                +"&serviceKey=a08f16c3f1fa964e73d7f89c12d0ba23c237661b69c06b2b11143d8451c3a0de";
//       JSON 온통청년 청년정책
        String url2 = "https://www.youthcenter.go.kr/go/ythip/getPlcy"
                +"?apiKeyNm=33142eb1-009c-4456-bb7d-c7887f95bca8"
                +"&pageNum=1"
                +"&pageSize=40"
                +"&returnType=JSON";

        RestTemplate restTemplate = new RestTemplate();
        String json =  restTemplate.getForObject(url2, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, RootDTO.class);
    }
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

}